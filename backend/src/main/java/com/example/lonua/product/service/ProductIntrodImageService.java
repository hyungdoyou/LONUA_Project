package com.example.lonua.product.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.lonua.product.model.entity.Product;
import com.example.lonua.product.model.entity.ProductIntrodImage;
import com.example.lonua.product.repository.ProductIntrodImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductIntrodImageService {

    @Value("${cloud.aws.s3.productIntrod-bucket}")
    private String productIntrodBucket;

    private final AmazonS3 s3;
    private final ProductIntrodImageRepository productIntrodImageRepository;


    public String makeFolder(){
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("/", File.separator);
        return folderPath;
    }

    public String saveFile(MultipartFile productIntrodFile) {

        String originalName = productIntrodFile.getOriginalFilename();

        String folderPath = makeFolder();
        String uuid = UUID.randomUUID().toString();
        String saveFileName = folderPath+ File.separator + uuid + "_" + originalName;

        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(productIntrodFile.getSize());
            metadata.setContentType(productIntrodFile.getContentType());

            s3.putObject(productIntrodBucket, saveFileName.replace(File.separator, "/"), productIntrodFile.getInputStream(), metadata);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // 로컬 파일 시스템에서 파일 삭제
            File localFile = new File(saveFileName);
            if (localFile.exists()) {
                localFile.delete();
            }
        }
        return s3.getUrl(productIntrodBucket, saveFileName.replace(File.separator, "/")).toString();
    }

    @Transactional(readOnly = false)
    public List<String> registerProductIntrodImage(Product product, MultipartFile[] uploadFiles) {

        List<String> productIntrodImageList = new ArrayList<>();
        for (MultipartFile multipartFile : uploadFiles) {

            String saveFileName = saveFile(multipartFile);

            productIntrodImageRepository.save(ProductIntrodImage.builder()
                    .product(product)
                    .productIntrodImage(saveFileName.replace(File.separator, "/"))
                    .build());

            productIntrodImageList.add(saveFileName.replace(File.separator, "/"));
        }

        return productIntrodImageList;
    }
}
