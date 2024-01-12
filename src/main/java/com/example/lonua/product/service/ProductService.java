package com.example.lonua.product.service;

import com.example.lonua.brand.model.entity.Brand;
import com.example.lonua.category.model.entity.Category;
import com.example.lonua.exception.ErrorCode;
import com.example.lonua.exception.exception.CategoryException;
import com.example.lonua.product.model.entity.ProductIntrod;
import com.example.lonua.product.model.request.PostRegisterProductReq;
import com.example.lonua.product.model.entity.Product;
import com.example.lonua.product.model.response.GetListProductRes;
import com.example.lonua.product.model.response.GetReadProductRes;
import com.example.lonua.product.model.response.GetProductIntrodRes;
import com.example.lonua.product.model.response.PostRegisterProductRes;
import com.example.lonua.product.repository.ProductIntrodRepository;
import com.example.lonua.product.repository.ProductRepository;
import com.example.lonua.style.model.entity.Style;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Value("${project.upload.product-path}")
    private String uploadProductPath;

//    @Value("${project.upload.product-introduction-path}")
//    private String uploadProductIntroductionPath;
    private final ProductRepository productRepository;
    private final ProductIntrodRepository productIntrodRepository;

    public ProductService(ProductRepository productRepository, ProductIntrodRepository productIntrodRepository) {
        this.productRepository = productRepository;
        this.productIntrodRepository = productIntrodRepository;
    }

    public String makeProductFolder(){
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String productFolderPath = str.replace("/", File.separator);

        File uploadProductPathFolder = new File(uploadProductPath, productFolderPath);

        if(uploadProductPathFolder.exists() == false) {
            uploadProductPathFolder.mkdirs();
        }

        // 폴더 경로를 반환한다.
        return productFolderPath;
    }

    public String saveProductFile(MultipartFile productFile) {
        String originalName = productFile.getOriginalFilename();

        String productFolderPath = makeProductFolder();

        String uuid = UUID.randomUUID().toString();

        String saveProductFileName = productFolderPath+ File.separator + uuid + "_" + originalName;
        // 해당 경로에 파일을 생성한다.
        File saveProductFile = new File(uploadProductPath, saveProductFileName);

        try {
            productFile.transferTo(saveProductFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return saveProductFileName;
    }


//public String makeFolder(String originalName){
//    String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
//    String folderPath = str.replace("/", File.separator);
//
//    if(originalName.contains("설명")) {
//        File uploadProductIntroductionPathFolder = new File(uploadProductIntroductionPath, folderPath);
//
//        if(uploadProductIntroductionPathFolder.exists() == false) {
//            uploadProductIntroductionPathFolder.mkdirs();
//        }
//        return folderPath;
//    } else {
//        File uploadProductPathFolder = new File(uploadProductPath, folderPath);
//
//        if(uploadProductPathFolder.exists() == false) {
//            uploadProductPathFolder.mkdirs();
//        }
//        // 폴더 경로를 반환한다.
//        return folderPath;
//    }
//}
//
//    public String saveFile(MultipartFile file) {
//        String originalName = file.getOriginalFilename();
//
//        String folderPath = makeFolder(originalName);
//
//        String uuid = UUID.randomUUID().toString();
//
//        if(originalName.contains("설명")) {
//            String saveFileName = folderPath+ File.separator + uuid + "_" + originalName;
//            File saveFile = new File(uploadProductIntroductionPath, saveFileName);
//            try {
//                file.transferTo(saveFile);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            return saveFileName;
//        } else {
//            String saveFileName = folderPath+ File.separator + uuid + "_" + originalName;
//            File saveFile = new File(uploadProductPath, saveFileName);
//            try {
//                file.transferTo(saveFile);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            return saveFileName;
//        }
//    }


    public PostRegisterProductRes register(PostRegisterProductReq postRegisterProductReq) {
        Optional<Product> result = productRepository.findByProductName(postRegisterProductReq.getProductName());
        if(result.isPresent()) {
            throw new CategoryException(ErrorCode.DUPLICATED_PRODUCT, String.format("Product is %s", postRegisterProductReq.getProductName()));
        }

        String saveProductFileName = saveProductFile(postRegisterProductReq.getProductImage());

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
                .productImage(saveProductFileName.replace(File.separator, "/"))
                .quantity(postRegisterProductReq.getQuantity())
                .price(postRegisterProductReq.getPrice())
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
                .upperType1Count(0)
                .upperType2Count(0)
                .upperType3Count(0)
                .lowerType1Count(0)
                .lowerType2Count(0)
                .lowerType3Count(0)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .status(1)
                .build();

        Product regProduct = productRepository.save(product);

        PostRegisterProductRes response = PostRegisterProductRes.builder()
                .productIdx(regProduct.getProductIdx())
                .productName(regProduct.getProductName())
                .productImage(regProduct.getProductImage())
                .quantity(regProduct.getQuantity())
                .price(regProduct.getPrice())
                .shoulderWidth(regProduct.getShoulderWidth())
                .chestSize(regProduct.getChestSize())
                .armLength(regProduct.getArmLength())
                .topLength(regProduct.getTopLength())
                .waistline(regProduct.getWaistline())
                .hipCircumference(regProduct.getHipCircumference())
                .thighCircumference(regProduct.getThighCircumference())
                .crotchLength(regProduct.getCrotchLength())
                .hemLength(regProduct.getHemLength())
                .totalBottomLength(regProduct.getTotalBottomLength())
                .build();

        return response;
    }

    // 상품 리스트 출력
    public List<GetListProductRes> list() {

        List<Product> result = productRepository.findAll();

        List<GetListProductRes> getListProductResList = new ArrayList<>();

        for(Product product : result) {
            GetListProductRes getListProductRes = GetListProductRes.builder()
                    .brandName(product.getBrand().getBrandName())
                    .productIdx(product.getProductIdx())
                    .productName(product.getProductName())
                    .productImage(product.getProductImage())
                    .price(product.getPrice())
                    .build();

            getListProductResList.add(getListProductRes);
        }
        return getListProductResList;
    }

    // 상품 1개 출력
    public GetReadProductRes read(Integer productIdx) {
        Optional<Product> result = productRepository.findByProductIdx(productIdx);


        if(result.isPresent()) {
            Product product = result.get();

            List<GetProductIntrodRes> getProductIntrodResList = new ArrayList<>();
            List<ProductIntrod> productIntrodList = product.getProductIntrodList();

            for(ProductIntrod productIntrod : productIntrodList) {
                GetProductIntrodRes getProductIntrodRes = GetProductIntrodRes.builder()
                        .productIntrodImage(productIntrod.getProductIntrodImage())
                        .build();

                getProductIntrodResList.add(getProductIntrodRes);
            }

            GetReadProductRes response = GetReadProductRes.builder()
                    .productIdx(product.getProductIdx())
                    .brandName(product.getBrand().getBrandName())
                    .productName(product.getProductName())
                    .productImage(product.getProductImage())
                    .getProductIntrodResList(getProductIntrodResList)
                    .price(product.getPrice())
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
                    .build();

            return response;
        } else {
            return null;
        }
    }
}
