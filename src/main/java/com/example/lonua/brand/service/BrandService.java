package com.example.lonua.brand.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.lonua.brand.model.request.PostRegisterBrandReq;
import com.example.lonua.brand.model.entity.Brand;
import com.example.lonua.brand.model.response.PostRegisterBrandRes;
import com.example.lonua.brand.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class BrandService {

//    @Value("${project.upload.path}")
//    private String uploadPath;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private AmazonS3 s3;
    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository, AmazonS3 s3) {
        this.brandRepository = brandRepository;
        this.s3 = s3;
    }

    public String makeFolder(){
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("/", File.separator);

//        File uploadPathFolder = new File(uploadPath, folderPath);
//
//        if(uploadPathFolder.exists() == false) {
//            uploadPathFolder.mkdirs();
//        }
        return folderPath;
    }

    // 파일을 저장하는 작업
    public String saveFile(MultipartFile file) {
        String originalName = file.getOriginalFilename();

        String folderPath = makeFolder();

        String uuid = UUID.randomUUID().toString();

        String saveFileName = folderPath+ File.separator + uuid + "_" + originalName;
//        File saveFile = new File(uploadPath, saveFileName);
        File saveFile = new File(saveFileName);

        try {
            //file.transferTo(saveFile);

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            s3.putObject(bucket, saveFileName.replace(File.separator, "/"), file.getInputStream(), metadata);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return s3.getUrl(bucket, saveFileName.replace(File.separator, "/")).toString();

    }


    public PostRegisterBrandRes register(PostRegisterBrandReq postRegisterBrandReq) {

        String saveFileName = saveFile(postRegisterBrandReq.getBrandImage());

        if(!brandRepository.findByBrandName(postRegisterBrandReq.getBrandName()).isPresent()) {
            Brand brand = Brand.builder()
                    .brandName(postRegisterBrandReq.getBrandName())
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
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .status(1)
                    .build();

            Brand result = brandRepository.save(brand);

            PostRegisterBrandRes response = PostRegisterBrandRes.builder()
                    .brandIdx(result.getBrandIdx())
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
                    .build();

            return response;
        } else {
            return null;
        }
    }
}
