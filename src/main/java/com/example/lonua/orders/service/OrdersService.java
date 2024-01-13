package com.example.lonua.orders.service;


import com.example.lonua.config.BaseRes;
import com.example.lonua.orders.model.entity.Orders;
import com.example.lonua.orders.model.entity.OrdersProduct;
import com.example.lonua.orders.model.request.PostCreateOrdersReq;
import com.example.lonua.orders.model.response.GetListOrdersRes;
import com.example.lonua.orders.model.response.GetReadOrdersRes;
import com.example.lonua.orders.model.response.PostCreateOrdersRes;
import com.example.lonua.orders.repository.OrdersProductRepository;
import com.example.lonua.orders.repository.OrdersRepository;
import com.example.lonua.product.model.entity.Product;
import com.example.lonua.product.model.entity.ProductCount;
import com.example.lonua.product.model.response.GetReadOrdersProductRes;
import com.example.lonua.product.repository.ProductCountRepository;
import com.example.lonua.product.repository.ProductRepository;
import com.example.lonua.user.model.entity.User;
import com.example.lonua.user.model.entity.response.PostUserOrdersRes;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final IamportClient iamportClient;
    private final ProductRepository productRepository;
    private final OrdersRepository ordersRepository;
    private final OrdersProductRepository ordersProductRepository;
    private final ProductCountRepository productCountRepository;


    @Transactional
    public BaseRes createOrder(User user, PostCreateOrdersReq postCreateOrdersReq) {
        Orders orders = Orders.builder()
                .user(user)
                .impUid(postCreateOrdersReq.getImpUid())
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .status(true)
                .build();

        orders = ordersRepository.save(orders);

        List<GetListOrdersRes> getListOrdersResList = new ArrayList<>();

        for (Integer idx : postCreateOrdersReq.getProductIdxList()) {
            Optional<Product> productIdx = productRepository.findByProductIdx(idx);

            if(productIdx.isPresent()) {
                // 주문 상품 DB에 추가
                OrdersProduct ordersProduct = OrdersProduct.builder()
                        .orders(orders)
                        .product(Product.builder().productIdx(idx).build())
                        .build();

                ordersProductRepository.save(ordersProduct);

                // 회원 상, 하체 유형에 따라 상품의 카운트 수 증가
                Optional<ProductCount> result = productCountRepository.findByProduct_ProductIdx(idx);
                ProductCount productCount = result.get();
                productCount.increaseUpperCount(user.getUpperType());
                productCount.increaseLowerCount(user.getLowerType());

                Optional<Product> product = productRepository.findByProductIdx(idx);
                Product userProduct = product.get();

                GetListOrdersRes getListOrdersRes = GetListOrdersRes.builder()
                        .brandName(userProduct.getBrand().getBrandName())
                        .productName(userProduct.getProductName())
                        .price(userProduct.getPrice())
                        .build();

                getListOrdersResList.add(getListOrdersRes);
            } else {
                return BaseRes.builder()
                        .code(400)
                        .isSuccess(false)
                        .message("상품 주문 실패")
                        .result("잘못된 요청입니다.")
                        .build();
            }
        }

        PostCreateOrdersRes postCreateOrdersRes = PostCreateOrdersRes.builder()
                .userName(user.getName())
                .userAddr(user.getUserAddr())
                .userPhoneNumber(user.getUserPhoneNumber())
                .totalAmount(postCreateOrdersReq.getAmount())
                .impUid(postCreateOrdersReq.getImpUid())
                .payMethod(postCreateOrdersReq.getPayMethod())
                .productList(getListOrdersResList)
                .build();

        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("상품 주문 성공")
                .result(postCreateOrdersRes)
                .build();
    }


    //----------------------------------카카오페이 결제를 통한 주문------------------------------------------------
//    @Transactional
//    public void createOrder(User user, PostCreateOrdersReq postCreateOrdersReq) {
//        Orders orders = Orders.builder()
//                .user(user)
//                .impUid(postCreateOrdersReq.getImpUid())
//                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
//                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
//                .status(true)
//                .build();
//
//        orders = ordersRepository.save(orders);
//
//        for (Integer idx : postCreateOrdersReq.getProductIdxList()) {
//            // 주문 상품 DB에 추가
//            ordersProductRepository.save(OrdersProduct.builder()
//                    .orders(orders)
//                    .product(Product.builder().productIdx(idx).build())
//                    .build());
//
//            // 회원 상, 하체 유형에 따라 상품의 카운트 수 증가
//            Optional<ProductCount> result = productCountRepository.findByProduct_ProductIdx(idx);
//            ProductCount productCount = result.get();
//            productCount.increaseUpperCount(user.getUpperType());
//            productCount.increaseLowerCount(user.getLowerType());
//        }
//    }
//
//    public BaseRes paymentValidation(User user, PostCreateOrdersReq postCreateOrdersReq) throws IamportResponseException, IOException {
//
//        Integer totalPrice = 0;
//        for(Integer idx : postCreateOrdersReq.getProductIdxList()) {
//            Optional<Product> result = productRepository.findByProductIdx(idx);
//            if(result.isPresent()) {
//                Product product = result.get();
//                totalPrice += product.getPrice();
//            }
//        }
//
//        if(postCreateOrdersReq.getAmount().equals(totalPrice) ) {
//            createOrder(user, postCreateOrdersReq);  // 주문 생성
//
//            List<GetListOrdersRes> getListOrdersResList = new ArrayList<>();
//            for(Integer idx : postCreateOrdersReq.getProductIdxList()) {
//                Optional<Product> result = productRepository.findByProductIdx(idx);
//
//                Product product = result.get();
//                GetListOrdersRes getListOrdersRes = GetListOrdersRes.builder()
//                        .brandName(product.getBrand().getBrandName())
//                        .productName(product.getProductName())
//                        .price(product.getPrice())
//                        .build();
//
//                getListOrdersResList.add(getListOrdersRes);
//            }
//
//            PostCreateOrdersRes postCreateOrdersRes = PostCreateOrdersRes.builder()
//                    .userName(user.getName())
//                    .userAddr(user.getUserAddr())
//                    .userPhoneNumber(user.getUserPhoneNumber())
//                    .totalAmount(postCreateOrdersReq.getAmount())
//                    .impUid(postCreateOrdersReq.getImpUid())
//                    .payMethod(postCreateOrdersReq.getPayMethod())
//                    .productList(getListOrdersResList)
//                    .build();
//
//            BaseRes baseRes = BaseRes.builder()
//                    .code(200)
//                    .isSuccess(true)
//                    .message("상품 주문 성공")
//                    .result(postCreateOrdersRes)
//                    .build();
//
//            return baseRes;
//        } else{
//            cancelPayment(postCreateOrdersReq.getImpUid());  // 취소 기능
//            return null;
//        }
//    }
//
//    public IamportResponse getPaymentInfo(String impUid) throws IamportResponseException, IOException {
//        IamportResponse<Payment> response = iamportClient.paymentByImpUid(impUid);
//
//        return response;
//    }
//
//    public void cancelPayment(String impUid) throws IamportResponseException, IOException {
//        CancelData cancelData = new CancelData(impUid,true);
//        iamportClient.cancelPaymentByImpUid(cancelData);
//    }
    //--------------------------------------------여기까지---------------------------------------------------------

//    public List<GetListOrdersRes> list() {
//        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        Integer userIdx = user.getUserIdx();
//
//        List<Orders> result = ordersRepository.findAll();
//
//        List<GetListOrdersRes> getListOrdersResList = new ArrayList<>();
//
//        for(Orders orders : result) {
//            User loginUser = orders.getUser();
//
//            if(loginUser.getUserIdx() == userIdx) {
//                GetReadOrdersProductRes getReadOrdersProductRes = GetReadOrdersProductRes.builder()
//                        .productIdx(orders.getProduct().getProductIdx())
//                        .productName(orders.getProduct().getProductName())
//                        .price(orders.getProduct().getPrice())
//                        .build();
//
//                GetListOrdersRes getListOrdersRes = GetListOrdersRes.builder()
//                        .ordersIdx(orders.getOrdersIdx())
//                        .getReadOrdersProductRes(getReadOrdersProductRes)
//                        .build();
//
//                getListOrdersResList.add(getListOrdersRes);
//            }
//        }
//        return getListOrdersResList;
//    }
//
//    public GetReadOrdersRes read(Integer ordersIdx) {
//        Optional<Orders> result = ordersRepository.findByOrdersIdx(ordersIdx);
//
//        if(result.isPresent()) {
//            Orders orders = result.get();
//
//            GetReadOrdersRes response = GetReadOrdersRes.builder()
//                    .ordersIdx(orders.getOrdersIdx())
//                    .postUserOrdersRes(PostUserOrdersRes.builder()
//                            .userIdx(orders.getUser().getUserIdx())
//                            .userEmail(orders.getUser().getUserEmail())
//                            .userName(orders.getUser().getUsername())
//                            .userPhoneNumber(orders.getUser().getUserPhoneNumber())
//                            .userAddr(orders.getUser().getUserAddr())
//                            .build())
//                    .getReadOrdersProductRes(GetReadOrdersProductRes.builder()
//                            .productIdx(orders.getProduct().getProductIdx())
//                            .brandName(orders.getProduct().getBrand().getBrandName())
//                            .productName(orders.getProduct().getProductName())
//                            .price(orders.getProduct().getPrice())
//                            .build())
//                    .build();
//
//            return response;
//        } else {
//            return null;
//        }
//    }
}
