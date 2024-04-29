package com.example.lonua.review.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.lonua.common.BaseRes;
import com.example.lonua.product.model.entity.Product;
import com.example.lonua.review.exception.ReviewNotFoundException;
import com.example.lonua.review.model.entity.Review;
import com.example.lonua.review.model.request.*;
import com.example.lonua.review.model.response.GetListReviewRes;
import com.example.lonua.review.model.response.GetReadReviewRes;
import com.example.lonua.review.model.response.PatchUpdateReviewRes;
import com.example.lonua.review.model.response.PostRegisterReviewRes;
import com.example.lonua.review.repository.ReviewRepository;
import com.example.lonua.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
@RequiredArgsConstructor
public class ReviewService {

    private final AmazonS3 s3;

    @Value("${cloud.aws.s3.review-bucket}")
    private String bucket;

    private final ReviewRepository reviewRepository;

    public String makeFolder() {
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("/", File.separator);
        return folderPath;
    }

    public String saveFile(MultipartFile file) {
        String originalName = file.getOriginalFilename();

        String folderPath = makeFolder();
        String uuid = UUID.randomUUID().toString();
        String saveFileName = folderPath + File.separator + uuid + "_" + originalName;

        try {

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            s3.putObject(bucket, saveFileName.replace(File.separator, "/"), file.getInputStream(), metadata);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // 로컬 파일 시스템에서 파일 삭제
            File localFile = new File(saveFileName);
            if (localFile.exists()) {
                localFile.delete();
            }
            return s3.getUrl(bucket, saveFileName.replace(File.separator, "/")).toString();

        }
    }

    @Transactional(readOnly = false)
    public void deleteFile(String reviewPhoto) {
        try {
            s3.deleteObject(bucket, reviewPhoto);
        } catch (AmazonS3Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Transactional(readOnly = false)
    public BaseRes registerReview(User user, PostRegisterReviewReq postRegisterReviewReq, MultipartFile file) {

        Optional<Review> result = reviewRepository.findByProduct_productIdxAndUser_userIdx(postRegisterReviewReq.getProductIdx(), user.getUserIdx());

        if(result.isPresent()) {
            return  BaseRes.builder()
                .code(400)
                .isSuccess(false)
                .message("리뷰 등록 실패")
                .result("이미 등록된 리뷰입니다.")
                .build();
        } else {
            String reviewPhoto = saveFile(file);

        reviewRepository.save(Review.builder()
                .product(Product.builder().productIdx(postRegisterReviewReq.getProductIdx()).build())
                .user(user)
                .reviewContent(postRegisterReviewReq.getReviewContent())
                .reviewPhoto(reviewPhoto.replace(File.separator, "/"))
                .evaluation(postRegisterReviewReq.getEvaluation())
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .status(true)
                .build());

        PostRegisterReviewRes postRegisterReviewRes = PostRegisterReviewRes.builder()
                .reviewContent(postRegisterReviewReq.getReviewContent())
                .reviewPhoto(reviewPhoto.replace(File.separator, "/"))
                .evaluation(postRegisterReviewReq.getEvaluation())
                .build();

        BaseRes baseRes = BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("리뷰 등록 성공")
                .result(postRegisterReviewRes)
                .build();

        return baseRes;
        }
    }

    @Transactional(readOnly = true)
    public BaseRes readReview(Integer reviewIdx) {

        Optional<Review> result = reviewRepository.findByReviewIdx(reviewIdx);
        if (result.isPresent()) {
            Review review = result.get();

            GetReadReviewRes getReadReviewRes = GetReadReviewRes.builder()
                    .reviewContent(review.getReviewContent())
                    .reviewPhoto(review.getReviewPhoto())
                    .evaluation(review.getEvaluation())
                    .build();

            BaseRes baseRes = BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("요청 성공")
                    .result(getReadReviewRes)
                    .build();

            return baseRes;

        } else {
            throw new ReviewNotFoundException(reviewIdx);
        }

    }
    @Transactional(readOnly = true)
    public BaseRes list(User user) {

        List<Review> reviewList = reviewRepository.findByUser_UserIdx(user.getUserIdx());
        List<GetListReviewRes> response = new ArrayList<>();

        if(reviewList != null) {
            for (Review review : reviewList) {

                GetListReviewRes getListReviewRes = GetListReviewRes.builder()
                        .reviewIdx(review.getReviewIdx())
                        .productIdx(review.getProduct().getProductIdx())
                        .name(review.getUser().getName())
                        .brandName(review.getProduct().getBrand().getBrandName())
                        .productName(review.getProduct().getProductName())
                        .productImage(review.getProduct().getProductImageList().get(0).getProductImage())
                        .reviewContent(review.getReviewContent())
                        .reviewPhoto(review.getReviewPhoto())
                        .evaluation(review.getEvaluation())
                        .updatedAt(review.getUpdatedAt())
                        .build();
                response.add(getListReviewRes);
            }

            BaseRes baseRes = BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("요청 성공")
                    .result(response)
                    .build();

            return baseRes;
        } else {
            throw new ReviewNotFoundException(null);
        }
    }

    @Transactional(readOnly = true)
    public BaseRes listReview(Integer productIdx) {

        List<Review> reviewList = reviewRepository.findByProduct_ProductIdx(productIdx);
        List<GetListReviewRes> response = new ArrayList<>();

        if(reviewList != null) {
            for (Review review : reviewList) {

                GetListReviewRes getListReviewRes = GetListReviewRes.builder()
                        .name(review.getUser().getName())
                        .productName(review.getProduct().getProductName())
                        .productImage(review.getProduct().getProductImageList().get(0).getProductImage())
                        .reviewContent(review.getReviewContent())
                        .reviewPhoto(review.getReviewPhoto())
                        .evaluation(review.getEvaluation())
                        .updatedAt(review.getUpdatedAt())
                        .build();
                response.add(getListReviewRes);
            }

            BaseRes baseRes = BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("요청 성공")
                    .result(response)
                    .build();

            return baseRes;
        } else {
            throw new ReviewNotFoundException(null);
        }
    }


    @Transactional(readOnly = false)
    public BaseRes updateReview(PatchUpdateReviewReq request, MultipartFile reviewFile, User user) {

        Optional<Review> result = reviewRepository.findByReviewIdxAndUser_userIdx(request.getReviewIdx(), user.getUserIdx());

        if (result.isPresent()) {
            Review review = result.get();

            if (reviewFile != null) {
                deleteFile(review.getReviewPhoto());

                String saveFileName = saveFile(reviewFile);
                review.update(request, saveFileName.replace(File.separator, "/"));
                review.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
                reviewRepository.save(review);
            } else {
                review.update(request, null);
                review.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
                reviewRepository.save(review);
            }
            PatchUpdateReviewRes patchUpdateReviewRes = PatchUpdateReviewRes.builder()
                    .reviewContent(review.getReviewContent())
                    .reviewPhoto(review.getReviewPhoto())
                    .evaluation(review.getEvaluation())
                    .build();

            return BaseRes.builder()
                        .code(200)
                        .isSuccess(true)
                        .message("리뷰 수정 성공")
                        .result(patchUpdateReviewRes)
                        .build();

        } else {
            throw new ReviewNotFoundException(request.getReviewIdx());
        }
    }

    @Transactional(readOnly = false)
    public BaseRes deleteReview(Integer reviewIdx, User user) {
        Integer result = reviewRepository.deleteByReviewIdxAndUser_userIdx(reviewIdx, user.getUserIdx());

        if(!result.equals(0)) {
            return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("리뷰 삭제 성공")
                .result(reviewIdx)
                .build();
        } else {
            throw new ReviewNotFoundException(reviewIdx);
        }
    }
}
