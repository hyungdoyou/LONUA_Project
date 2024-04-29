package com.example.lonua.product.service;

import com.example.lonua.brand.model.entity.Brand;
import com.example.lonua.category.model.entity.Category;
import com.example.lonua.common.BaseRes;
import com.example.lonua.product.model.entity.ProductCount;
import com.example.lonua.product.model.entity.ProductImage;
import com.example.lonua.product.model.entity.ProductIntrodImage;
import com.example.lonua.product.model.request.PatchUpdateProductReq;
import com.example.lonua.product.model.request.PostRegisterProductReq;
import com.example.lonua.product.model.entity.Product;
import com.example.lonua.product.model.response.GetListProductRes;
import com.example.lonua.product.model.response.GetReadProductRes;
import com.example.lonua.product.model.response.PatchUpdateProductRes;
import com.example.lonua.product.model.response.PostRegisterProductRes;
import com.example.lonua.product.repository.ProductCountRepository;
import com.example.lonua.product.repository.ProductImageRepository;
import com.example.lonua.product.repository.ProductIntrodImageRepository;
import com.example.lonua.product.repository.ProductRepository;
import com.example.lonua.style.model.entity.Style;
import com.example.lonua.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImageService productImageService;
    private final ProductIntrodImageService productIntrodImageService;
    private final ProductCountRepository productCountRepository;
    private final ProductIntrodImageRepository productIntrodImageRepository;
    private final ProductImageRepository productImageRepository;


    @Transactional(readOnly = false)
    public BaseRes register(PostRegisterProductReq postRegisterProductReq, MultipartFile[] productFiles, MultipartFile[] productIntrodFiles) {

        Optional<Product> result = productRepository.findByProductName(postRegisterProductReq.getProductName());

        Product product = Product.builder()
                .brand(Brand.builder()
                        .brandIdx(postRegisterProductReq.getBrand_idx())
                        .build())
                .category(Category.builder()
                        .categoryIdx(postRegisterProductReq.getCategory_idx())
                        .build())
                .style(Style.builder()
                        .styleIdx(postRegisterProductReq.getStyle_idx())
                        .build())
                .productName(postRegisterProductReq.getProductName())
                .quantity(postRegisterProductReq.getQuantity())
                .price(postRegisterProductReq.getPrice())
                .salePrice(postRegisterProductReq.getSalePrice())
                .shoulderWidth(postRegisterProductReq.getShoulderWidth())
                .chestSize(postRegisterProductReq.getChestSize())
                .armLength(postRegisterProductReq.getArmLength())
                .topLength(postRegisterProductReq.getTopLength())
                .waistline(postRegisterProductReq.getWaistline())
                .hipCircumference(postRegisterProductReq.getHipCircumference())
                .thighCircumference(postRegisterProductReq.getThighCircumference())
                .crotchLength(postRegisterProductReq.getCrotchLength())
                .hemLength(postRegisterProductReq.getHemLength())
                .totalBottomLength(postRegisterProductReq.getTotalBottomLength())
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .status(false)
                .build();

        product = productRepository.save(product);

        productCountRepository.save(ProductCount.builder()
                .product(product)
                .likeCount(0)
                .upperType1Count(0)
                .upperType2Count(0)
                .upperType3Count(0)
                .lowerType1Count(0)
                .lowerType2Count(0)
                .lowerType3Count(0)
                .build());

        List<String> productImageList = productImageService.registerProductImage(product, productFiles);
        List<String> productIntrodImageList = productIntrodImageService.registerProductIntrodImage(product, productIntrodFiles);

        PostRegisterProductRes postRegisterProductRes = PostRegisterProductRes.builder()
                .productIdx(product.getProductIdx())
                .productName(product.getProductName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .salePrice(product.getSalePrice())
                .shoulderWidth(product.getShoulderWidth())
                .chestSize(product.getChestSize())
                .armLength(product.getArmLength())
                .topLength(product.getTopLength())
                .waistline(product.getWaistline())
                .hipCircumference(product.getHipCircumference())
                .thighCircumference(product.getThighCircumference())
                .crotchLength(product.getCrotchLength())
                .hemLength(product.getHemLength())
                .totalBottomLength(product.getTotalBottomLength())
                .productImage(productImageList)
                .productIntroductionImage(productIntrodImageList)
                .build();

        BaseRes baseRes = BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("상품 등록 성공")
                .result(postRegisterProductRes)
                .build();

        return baseRes;
    }

    // 상품 리스트 출력(페이지 별)

    @Transactional(readOnly = true)
    public BaseRes list(Integer page, Integer size) {

        // 페이징 기능 사용(QueryDSL)
        Pageable pageable = PageRequest.of(page-1, size);
        Page<Product> productList = productRepository.findList(pageable);

        List<GetListProductRes> getListProductResList = new ArrayList<>();

        for(Product product : productList) {

            List<ProductImage> productImageList = product.getProductImageList();
            ProductImage productImage = productImageList.get(0);
            String image = productImage.getProductImage();
            // 상품의 이미지중 첫번 째 이미지만 뽑아옴

            GetListProductRes getListProductRes = GetListProductRes.builder()
                        .brandName(product.getBrand().getBrandName())
                        .productIdx(product.getProductIdx())
                        .productName(product.getProductName())
                        .productImage(image)
                        .price(product.getPrice())
                        .salePrice(product.getSalePrice())
                        .likeCount(product.getProductCount().getLikeCount())
                        .build();

            getListProductResList.add(getListProductRes);
        }
        BaseRes baseRes = BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청 성공")
                .result(getListProductResList)
                .build();

        return baseRes;
    }

    // 상품 세부 정보 조회(read 역할)
    @Transactional(readOnly = true)
    public BaseRes read(Integer idx) {
        Optional<Product> result = productRepository.findProduct(idx);

        if(result.isPresent()) {
            Product product = result.get();

            List<ProductImage> productImageList = product.getProductImageList();
            List<ProductIntrodImage> productIntrodImageList = product.getProductIntrodImageList();

            List<String> productImages = new ArrayList<>();
            for(ProductImage productImage : productImageList) {
                productImages.add(productImage.getProductImage());
            }

            List<String> productIntrodImages = new ArrayList<>();
            for(ProductIntrodImage productIntrodImage : productIntrodImageList) {
                productIntrodImages.add(productIntrodImage.getProductIntrodImage());
            }

            GetReadProductRes getReadProductRes = GetReadProductRes.builder()
                    .productIdx(product.getProductIdx())
                    .productName(product.getProductName())
                    .productImages(productImages)
                    .productIntrodImages(productIntrodImages)
                    .price(product.getPrice())
                    .salePrice(product.getSalePrice())
                    .shoulderWidth(product.getShoulderWidth())
                    .chestSize(product.getChestSize())
                    .armLength(product.getArmLength())
                    .topLength(product.getTopLength())
                    .waistline(product.getWaistline())
                    .hipCircumference(product.getHipCircumference())
                    .thighCircumference(product.getThighCircumference())
                    .crotchLength(product.getCrotchLength())
                    .hemLength(product.getHemLength())
                    .totalBottomLength(product.getTotalBottomLength())
                    .brandName(product.getBrand().getBrandName())
                    .brandImage(product.getBrand().getBrandImage())
                    .brandPhoneNumber(product.getBrand().getPhoneNumber())
                    .businessAddress(product.getBrand().getBusinessAddress())
                    .businessRegistration(product.getBrand().getBusinessRegistration())
                    .returnAddress(product.getBrand().getReturnAddress())
                    .returnCost(product.getBrand().getReturnCost())
                    .returnCourier(product.getBrand().getReturnCourier())
                    .likeCount(product.getProductCount().getLikeCount())
                    .build();

            BaseRes baseRes = BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("요청 성공")
                    .result(getReadProductRes)
                    .build();

            return baseRes;
        } else {
            BaseRes baseRes = BaseRes.builder()
                    .code(400)
                    .isSuccess(false)
                    .message("요청 실패")
                    .result("잘못된 요청입니다.")
                    .build();

            return baseRes;
        }
    }

    @Transactional(readOnly = false)
    public BaseRes update(PatchUpdateProductReq patchUpdateProductReq) {
        Optional<Product> result = productRepository.findByProductIdx(patchUpdateProductReq.getProductIdx());

        if(result.isPresent()) {
            Product product = result.get();

            product.update(patchUpdateProductReq);
            product.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
            productRepository.save(product);

            PatchUpdateProductRes patchUpdateProductRes = PatchUpdateProductRes.builder()
                    .productName(product.getProductName())
                    .quantity(product.getQuantity())
                    .price(product.getPrice())
                    .salePrice(product.getSalePrice())
                    .build();

            BaseRes baseRes = BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("수정 요청 성공")
                    .result(patchUpdateProductRes)
                    .build();

            return baseRes;
        } else {
            BaseRes baseRes = BaseRes.builder()
                    .code(400)
                    .isSuccess(false)
                    .message("수정 요청 실패")
                    .result("요청을 수행할 수 없습니다.")
                    .build();

            return baseRes;
        }
    }

    @Transactional(readOnly = false)
    public BaseRes delete(Integer idx) {
        Integer result1 = productImageRepository.deleteAllByProduct_ProductIdx(idx);
        Integer result2 = productIntrodImageRepository.deleteAllByProduct_ProductIdx(idx);
        Integer result3 = productCountRepository.deleteByProduct_ProductIdx(idx);
        Integer result4 = productRepository.deleteByProductIdx(idx);

        if(!result1.equals(0) && !result2.equals(0) && !result3.equals(0)&& !result4.equals(0)) {
            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("요청 성공")
                    .result("상품이 삭제되었습니다.")
                    .build();
        } else {
            return BaseRes.builder()
                    .code(400)
                    .isSuccess(false)
                    .message("요청 실패")
                    .result("상품을 찾을 수 없습니다.")
                    .build();
        }
    }
    @Transactional(readOnly = true)
    public BaseRes categoryProductlist(Integer categoryIdx, Integer page, Integer size) {

        // 페이징 기능 사용(QueryDSL)
        Pageable pageable = PageRequest.of(page-1, size);
        Page<Product> productList = productRepository.findCategoryList(pageable, categoryIdx);

        List<GetListProductRes> getListProductResList = new ArrayList<>();
        for(Product product : productList) {

            List<ProductImage> productImageList = product.getProductImageList();
            ProductImage productImage = productImageList.get(0);
            String image = productImage.getProductImage();
            // 상품의 이미지중 첫번 째 이미지만 뽑아옴

            GetListProductRes getListProductRes = GetListProductRes.builder()
                    .brandName(product.getBrand().getBrandName())
                    .productIdx(product.getProductIdx())
                    .productName(product.getProductName())
                    .productImage(image)
                    .price(product.getPrice())
                    .salePrice(product.getSalePrice())
                    .likeCount(product.getProductCount().getLikeCount())
                    .build();

            getListProductResList.add(getListProductRes);
        }

        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청 성공")
                .result(getListProductResList)
                .build();
    }

    @Transactional(readOnly = true)
    public BaseRes styleProductlist(Integer styleIdx, Integer page, Integer size) {

        // 페이징 기능 사용(QueryDSL)
        Pageable pageable = PageRequest.of(page-1, size);
        Page<Product> productList = productRepository.findStyleList(pageable, styleIdx);

        List<GetListProductRes> getListProductResList = new ArrayList<>();
        for(Product product : productList) {

            List<ProductImage> productImageList = product.getProductImageList();
            ProductImage productImage = productImageList.get(0);
            String image = productImage.getProductImage();
            // 상품의 이미지중 첫번 째 이미지만 뽑아옴

            GetListProductRes getListProductRes = GetListProductRes.builder()
                    .brandName(product.getBrand().getBrandName())
                    .productIdx(product.getProductIdx())
                    .productName(product.getProductName())
                    .productImage(image)
                    .price(product.getPrice())
                    .salePrice(product.getSalePrice())
                    .likeCount(product.getProductCount().getLikeCount())
                    .build();

            getListProductResList.add(getListProductRes);
        }

        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청 성공")
                .result(getListProductResList)
                .build();
    }

    @Transactional(readOnly = true)
    public BaseRes sameTypeProductList(User user, Integer page, Integer size) {

        Integer upperType = user.getUpperType();
        Integer lowerType = user.getLowerType();

        // 페이징 기능 사용(QueryDSL)
        Pageable pageable = PageRequest.of(page-1, size);
        Page<Product> productList = productRepository.findSameTypeList(pageable, upperType, lowerType);

        List<GetListProductRes> getListProductResList = new ArrayList<>();
        for(Product product : productList) {

            List<ProductImage> productImageList = product.getProductImageList();
            ProductImage productImage = productImageList.get(0);
            String image = productImage.getProductImage();
            // 상품의 이미지중 첫번 째 이미지만 뽑아옴

            GetListProductRes getListProductRes = GetListProductRes.builder()
                    .brandName(product.getBrand().getBrandName())
                    .productIdx(product.getProductIdx())
                    .productName(product.getProductName())
                    .productImage(image)
                    .price(product.getPrice())
                    .salePrice(product.getSalePrice())
                    .likeCount(product.getProductCount().getLikeCount())
                    .build();

            getListProductResList.add(getListProductRes);
        }
        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청 성공")
                .result(getListProductResList)
                .build();
    }

    @Transactional(readOnly = true)
    public BaseRes brandProductList(Integer brandIdx, Integer page, Integer size) {

        // 페이징 기능 사용(QueryDSL)
        Pageable pageable = PageRequest.of(page-1, size);
        Page<Product> productList = productRepository.findBrandList(pageable, brandIdx);

        List<GetListProductRes> getListProductResList = new ArrayList<>();
        for(Product product : productList) {

            List<ProductImage> productImageList = product.getProductImageList();
            ProductImage productImage = productImageList.get(0);
            String image = productImage.getProductImage();
            // 상품의 이미지중 첫번 째 이미지만 뽑아옴

            GetListProductRes getListProductRes = GetListProductRes.builder()
                    .brandName(product.getBrand().getBrandName())
                    .productIdx(product.getProductIdx())
                    .productName(product.getProductName())
                    .productImage(image)
                    .price(product.getPrice())
                    .salePrice(product.getSalePrice())
                    .likeCount(product.getProductCount().getLikeCount())
                    .build();

            getListProductResList.add(getListProductRes);
        }
        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청 성공")
                .result(getListProductResList)
                .build();
    }
}

