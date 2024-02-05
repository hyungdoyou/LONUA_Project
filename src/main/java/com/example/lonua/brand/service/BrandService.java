package com.example.lonua.brand.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.lonua.brand.model.request.PatchUpdateBrandReq;
import com.example.lonua.brand.model.request.PostRegisterBrandReq;
import com.example.lonua.brand.model.entity.Brand;
import com.example.lonua.brand.model.response.*;
import com.example.lonua.brand.repository.BrandRepository;
import com.example.lonua.common.BaseRes;
import com.example.lonua.product.model.entity.Product;
import com.example.lonua.product.repository.ProductCountRepository;
import com.example.lonua.product.repository.ProductImageRepository;
import com.example.lonua.product.repository.ProductIntrodImageRepository;
import com.example.lonua.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BrandService {

    @Value("${cloud.aws.s3.brand-bucket}")
    private String brandBucket;

    private final AmazonS3 s3;
    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductIntrodImageRepository productIntrodImageRepository;
    private final ProductCountRepository productCountRepository;

    public String makeFolder(){
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("/", File.separator);
        return folderPath;
    }

    public void deleteFile(String brandImage) {
        try {
            s3.deleteObject(brandBucket, brandImage);
        } catch (AmazonS3Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String saveFile(MultipartFile brandFile) {
        String originalName = brandFile.getOriginalFilename();

        String folderPath = makeFolder();
        String uuid = UUID.randomUUID().toString();
        String saveFileName = folderPath+ File.separator + uuid + "_" + originalName;
        InputStream input = null;

        try {
            input = brandFile.getInputStream();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(brandFile.getSize());
            metadata.setContentType(brandFile.getContentType());

            s3.putObject(brandBucket, saveFileName.replace(File.separator, "/"), input, metadata);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // 로컬 파일 시스템에서 파일 삭제
            File localFile = new File(saveFileName);
            if (localFile.exists()) {
                localFile.delete();
            }
            return s3.getUrl(brandBucket, saveFileName.replace(File.separator, "/")).toString();
        }
    }

    public BaseRes register(PostRegisterBrandReq postRegisterBrandReq, MultipartFile brandFile) {

        String saveFileName = saveFile(brandFile);

        if(!brandRepository.findByBrandName(postRegisterBrandReq.getBrandName()).isPresent()) {
            Brand brand = Brand.builder()
                    .brandName(postRegisterBrandReq.getBrandName())
                    .brandUniqueKey(postRegisterBrandReq.getBrandUniqueKey())
                    .brandIntroduction(postRegisterBrandReq.getBrandIntroduction())
                    .brandImage(saveFileName.replace(File.separator, "/"))
                    .brandStyle(postRegisterBrandReq.getBrandStyle())
                    .businessAddress(postRegisterBrandReq.getBusinessAddress())
                    .phoneNumber(postRegisterBrandReq.getPhoneNumber())
                    .businessRegistration(postRegisterBrandReq.getBusinessRegistration())
                    .bankAccountNumber(postRegisterBrandReq.getBankAccountNumber())
                    .returnAddress(postRegisterBrandReq.getReturnAddress())
                    .returnCost(postRegisterBrandReq.getReturnCost())
                    .returnCourier(postRegisterBrandReq.getReturnCourier())
                    .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                    .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                    .status(true)
                    .build();

            Brand result = brandRepository.save(brand);

            PostRegisterBrandRes response = PostRegisterBrandRes.builder()
                    .brandName(result.getBrandName())
                    .brandIntroduction(result.getBrandIntroduction())
                    .brandImage(result.getBrandImage())
                    .brandStyle(result.getBrandStyle())
                    .businessAddress(result.getBusinessAddress())
                    .phoneNumber(result.getPhoneNumber())
                    .businessRegistration(result.getBusinessRegistration())
                    .bankAccountNumber(result.getBankAccountNumber())
                    .returnAddress(result.getReturnAddress())
                    .returnCost(result.getReturnCost())
                    .returnCourier(result.getReturnCourier())
                    .build();

            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("브랜드 등록 성공")
                    .result(response)
                    .build();
        } else {
            return BaseRes.builder()
                    .code(400)
                    .isSuccess(false)
                    .message("잘못된 요청입니다.")
                    .result("브랜드 등록 실패")
                    .build();
        }
    }

    public BaseRes list(Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page-1, size);
        Page<Brand> brandList = brandRepository.findAll(pageable);

        List<GetListBrandRes> getListBrandResList = new ArrayList<>();
        for (Brand brand : brandList) {
            GetListBrandRes getListBrandRes = GetListBrandRes.builder()
                    .brandIdx(brand.getBrandIdx())
                    .brandName(brand.getBrandName())
                    .brandImage(brand.getBrandImage())
                    .brandIntroduction(brand.getBrandIntroduction())
                    .brandStyle(brand.getBrandStyle())
                    .build();

            getListBrandResList.add(getListBrandRes);
        }

        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청 성공")
                .result(getListBrandResList)
                .build();
    }

    public BaseRes read(Integer idx) {

        Optional<Brand> result = brandRepository.findByBrandIdx(idx);

        if(result.isPresent()) {
            Brand brand = result.get();

            PostRegisterBrandRes response = PostRegisterBrandRes.builder()
                    .brandName(brand.getBrandName())
                    .brandIntroduction(brand.getBrandIntroduction())
                    .brandImage(brand.getBrandImage())
                    .brandStyle(brand.getBrandStyle())
                    .businessAddress(brand.getBusinessAddress())
                    .phoneNumber(brand.getPhoneNumber())
                    .businessRegistration(brand.getBusinessRegistration())
                    .bankAccountNumber(brand.getBankAccountNumber())
                    .returnAddress(brand.getReturnAddress())
                    .returnCost(brand.getReturnCost())
                    .build();

            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("요청 성공")
                    .result(response)
                    .build();
        } else {
            return BaseRes.builder()
                    .code(400)
                    .isSuccess(true)
                    .message("요청 실패")
                    .result("잘못된 요청입니다.")
                    .build();
        }
    }

    @Transactional
    public BaseRes update(PatchUpdateBrandReq patchUpdateBrandReq, MultipartFile brandFile) {

        Optional<Brand> result = brandRepository.findByBrandIdx(patchUpdateBrandReq.getBrandIdx());

        if(result.isPresent()) {
            if(brandFile != null) {
                Brand brand = result.get();
                System.out.println(brand.getBrandImage());

                // 기존 업로드된 이미지 파일 삭제
                deleteFile(brand.getBrandImage());

                // 새로운 이미지 업로드
                String saveFileName = saveFile(brandFile);
                brand.update(patchUpdateBrandReq, saveFileName.replace(File.separator, "/"));
                brand.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
                brandRepository.save(brand);


                PatchUpdateBrandRes patchUpdateBrandRes = PatchUpdateBrandRes.builder()
                        .brandName(brand.getBrandName())
                        .brandImage(brand.getBrandImage())
                        .brandIntroduction(brand.getBrandIntroduction())
                        .brandStyle(brand.getBrandStyle())
                        .businessAddress(brand.getBusinessAddress())
                        .phoneNumber(brand.getPhoneNumber())
                        .businessRegistration(brand.getBusinessRegistration())
                        .bankAccountNumber(brand.getBankAccountNumber())
                        .returnAddress(brand.getReturnAddress())
                        .returnCost(brand.getReturnCost())
                        .returnCourier(brand.getReturnCourier())
                        .build();

                return BaseRes.builder()
                        .code(200)
                        .isSuccess(true)
                        .message("요청 성공")
                        .result(patchUpdateBrandRes)
                        .build();

            } else {
                Brand brand = result.get();
                brand.update(patchUpdateBrandReq, null);

                PatchUpdateBrandRes patchUpdateBrandRes = PatchUpdateBrandRes.builder()
                        .brandName(brand.getBrandName())
                        .brandImage(brand.getBrandImage())
                        .brandIntroduction(brand.getBrandIntroduction())
                        .brandStyle(brand.getBrandStyle())
                        .businessAddress(brand.getBusinessAddress())
                        .phoneNumber(brand.getPhoneNumber())
                        .businessRegistration(brand.getBusinessRegistration())
                        .bankAccountNumber(brand.getBankAccountNumber())
                        .returnAddress(brand.getReturnAddress())
                        .returnCost(brand.getReturnCost())
                        .returnCourier(brand.getReturnCourier())
                        .build();

                return BaseRes.builder()
                        .code(200)
                        .isSuccess(true)
                        .message("요청 성공")
                        .result(patchUpdateBrandRes)
                        .build();
            }
        } else {
            return BaseRes.builder()
                    .code(400)
                    .isSuccess(false)
                    .message("요청 실패")
                    .result("잘못된 요청입니다.")
                    .build();
        }
    }

    @Transactional
    public BaseRes delete(Integer idx) {
        List<Product> productList = productRepository.findAllByBrand_BrandIdx(idx);

        for(Product product : productList) {
            Integer result1 = productImageRepository.deleteAllByProduct_ProductIdx(product.getProductIdx());
            Integer result2 = productIntrodImageRepository.deleteAllByProduct_ProductIdx(product.getProductIdx());
            Integer result3 = productCountRepository.deleteByProduct_ProductIdx(product.getProductIdx());
            Integer result4 = productRepository.deleteByProductIdx(product.getProductIdx());

            if(result1.equals(0) || result2.equals(0) || result3.equals(0) || result4.equals(0)) {
                return BaseRes.builder()
                        .code(400)
                        .isSuccess(false)
                        .message("요청 실패")
                        .result("요청을 수행할 수 없습니다.")
                        .build();
            }
        }

        Integer result = brandRepository.deleteByBrandIdx(idx);

        if(!result.equals(0)) {
            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("요청 성공")
                    .result("브랜드가 삭제되었습니다.")
                    .build();
        } else {
            return BaseRes.builder()
                    .code(400)
                    .isSuccess(false)
                    .message("요청 실패")
                    .result("브랜드를 찾을 수 없습니다.")
                    .build();
        }
    }

    public BaseRes listAll() {

//        Pageable pageable = PageRequest.of(page-1, size);
        List<Brand> brandList = brandRepository.findAll();

        List<GetListAllBrandRes> getListAllBrandResList = new ArrayList<>();
        for (Brand brand : brandList) {
            List<Product> productList = productRepository.findAllByBrand_BrandIdx(brand.getBrandIdx());

            List<GetBrandProductRes> getBrandProductResList = new ArrayList<>();
            for(Product product : productList) {
                GetBrandProductRes getBrandProductRes = GetBrandProductRes.builder()
                        .productIdx(product.getProductIdx())
                        .brandIdx(product.getBrand().getBrandIdx())
                        .productName(product.getProductName())
                        .productImage(product.getProductImageList().get(0).getProductImage())
                        .price(product.getPrice())
                        .salePrice(product.getSalePrice())
                        .likeCount(product.getProductCount().getLikeCount())
                        .build();

                getBrandProductResList.add(getBrandProductRes);
            }
            GetListAllBrandRes getListAllBrandRes = GetListAllBrandRes.builder()
                    .brandIdx(brand.getBrandIdx())
                    .brandName(brand.getBrandName())
                    .brandImage(brand.getBrandImage())
                    .brandIntroduction(brand.getBrandIntroduction())
                    .brandStyle(brand.getBrandStyle())
                    .getBrandProductResList(getBrandProductResList)
                    .build();

            getListAllBrandResList.add(getListAllBrandRes);
        }

        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청 성공")
                .result(getListAllBrandResList)
                .build();
    }
}
