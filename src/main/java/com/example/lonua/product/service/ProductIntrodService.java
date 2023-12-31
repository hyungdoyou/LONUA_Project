package com.example.lonua.product.service;

import com.example.lonua.product.model.entity.Product;
import com.example.lonua.product.model.entity.ProductIntrod;
import com.example.lonua.product.model.request.PostRegisterProductIntrodReq;
import com.example.lonua.product.repository.ProductIntrodRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class ProductIntrodService {

    @Value("${project.upload.product-introduction-path}")
    private String uploadProductIntroductionPath;
    private final ProductIntrodRepository productIntrodRepository;

    public ProductIntrodService(ProductIntrodRepository productIntrodRepository) {
        this.productIntrodRepository = productIntrodRepository;
    }

    public String makeFolder(){
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("/", File.separator);

        File uploadProductIntroductionPathFolder = new File(uploadProductIntroductionPath, folderPath);

        if(uploadProductIntroductionPathFolder.exists() == false) {
            uploadProductIntroductionPathFolder.mkdirs();
        }
        return folderPath;
    }

    public String saveFile(MultipartFile productIntrodFile) {
        String originalName = productIntrodFile.getOriginalFilename();
        String folderPath = makeFolder();

        String uuid = UUID.randomUUID().toString();

        String saveFileName = folderPath+ File.separator + uuid + "_" + originalName;
        File saveFile = new File(uploadProductIntroductionPath, saveFileName);

        try {
            productIntrodFile.transferTo(saveFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return saveFileName;
    }
    public void register(PostRegisterProductIntrodReq postRegisterProductIntrodReq) {

        String saveProductIntrodFileName = saveFile(postRegisterProductIntrodReq.getProductIntrodImage());

        productIntrodRepository.save(ProductIntrod.builder()
                .product(Product.builder().productIdx(postRegisterProductIntrodReq.getProduct_idx()).build())
                .productIntrodImage(saveProductIntrodFileName.replace(File.separator, "/"))
                .build());
    }
}
