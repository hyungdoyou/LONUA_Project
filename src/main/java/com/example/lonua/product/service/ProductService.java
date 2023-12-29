package com.example.lonua.product.service;

import com.example.lonua.brand.model.entity.Brand;
import com.example.lonua.category.model.entity.Category;
import com.example.lonua.exception.ErrorCode;
import com.example.lonua.exception.exception.CategoryException;
import com.example.lonua.product.model.request.PostRegisterProductReq;
import com.example.lonua.product.model.entity.Product;
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
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Value("${project.upload.product-path}")
    private String uploadProductPath;

    @Value("${project.upload.product-introduction-path}")
    private String uploadProductIntroductionPath;
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
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

    public String makeProductIntroductionFolder(){
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String productIntrocutionFolderPath = str.replace("/", File.separator);

        File uploadProductIntroductionPathFolder = new File(uploadProductIntroductionPath, productIntrocutionFolderPath);

        if(uploadProductIntroductionPathFolder.exists() == false) {
            uploadProductIntroductionPathFolder.mkdirs();
        }

        // 폴더 경로를 반환한다.
        return productIntrocutionFolderPath;
    }

    public String saveProductIntroductionFile(MultipartFile productIntroductionFile) {
        String originalName = productIntroductionFile.getOriginalFilename();
        String productIntrocutionFolderPath = makeProductIntroductionFolder();

        String uuid = UUID.randomUUID().toString();

        String saveProductIntroductionFileName = productIntrocutionFolderPath+ File.separator + uuid + "_" + originalName;
        // 해당 경로에 파일을 생성한다.
        File saveProductIntroductionFile = new File(uploadProductIntroductionPath, saveProductIntroductionFileName);

        try {
            productIntroductionFile.transferTo(saveProductIntroductionFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return saveProductIntroductionFileName;
    }


    public void register(PostRegisterProductReq postRegisterProductReq) {
        Optional<Product> result = productRepository.findByProductName(postRegisterProductReq.getProductName());
        if(result.isPresent()) {
            throw new CategoryException(ErrorCode.DUPLICATED_PRODUCT, String.format("Product is %s", postRegisterProductReq.getProductName()));
        }

        String saveProductFileName = saveProductFile(postRegisterProductReq.getProductImage());
        String saveProductIntroductionFileName = saveProductIntroductionFile(postRegisterProductReq.getProductIntroductionImage());


        productRepository.save(Product.builder()
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
                        .productIntroductionImage(saveProductIntroductionFileName.replace(File.separator, "/"))
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
                        .upperType1Amount(0)
                        .upperType2Amount(0)
                        .upperType3Amount(0)
                        .lowerType1Amount(0)
                        .lowerType2Amount(0)
                        .lowerType3Amount(0)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .status(1)
                .build());
    }
}
