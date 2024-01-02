package com.example.lonua.orders.service;


import com.example.lonua.orders.model.entity.Orders;
import com.example.lonua.orders.model.request.PostRegisterOrdersReq;
import com.example.lonua.orders.model.response.GetListOrdersRes;
import com.example.lonua.orders.model.response.GetReadOrdersRes;
import com.example.lonua.orders.repository.OrdersRepository;
import com.example.lonua.product.model.entity.Product;
import com.example.lonua.product.model.response.GetReadOrdersProductRes;
import com.example.lonua.product.repository.ProductRepository;
import com.example.lonua.user.model.entity.User;
import com.example.lonua.user.model.entity.response.PostUserLoginRes;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final ProductRepository productRepository;

    public OrdersService(OrdersRepository ordersRepository, ProductRepository productRepository) {
        this.ordersRepository = ordersRepository;
        this.productRepository = productRepository;
    }

    public void register(Integer userIdx, PostRegisterOrdersReq postRegisterOrdersReq) {

        Orders orders = Orders.builder()
                .user(User.builder().userIdx(userIdx).build())
                .product(Product.builder().productIdx(postRegisterOrdersReq.getProduct_idx()).build())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .status(1)
                .build();
    }

    public List<GetListOrdersRes> list() {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Integer userIdx = user.getUserIdx();

        List<Orders> result = ordersRepository.findAll();

        List<GetListOrdersRes> getListOrdersResList = new ArrayList<>();

        for(Orders orders : result) {
            User loginUser = orders.getUser();

            if(loginUser.getUserIdx() == userIdx) {
                GetReadOrdersProductRes getReadOrdersProductRes = GetReadOrdersProductRes.builder()
                        .productIdx(orders.getProduct().getProductIdx())
                        .productName(orders.getProduct().getProductName())
                        .price(orders.getProduct().getPrice())
                        .build();

                GetListOrdersRes getListOrdersRes = GetListOrdersRes.builder()
                        .ordersIdx(orders.getOrdersIdx())
                        .getReadOrdersProductRes(getReadOrdersProductRes)
                        .build();

                getListOrdersResList.add(getListOrdersRes);
            }
        }
        return getListOrdersResList;
    }

    public GetReadOrdersRes read(Integer ordersIdx) {
        Optional<Orders> result = ordersRepository.findByOrdersIdx(ordersIdx);

        if(result.isPresent()) {
            Orders orders = result.get();

            GetReadOrdersRes response = GetReadOrdersRes.builder()
                    .ordersIdx(orders.getOrdersIdx())
                    .postUserLoginRes(PostUserLoginRes.builder()
                            .userIdx(orders.getUser().getUserIdx())
                            .userId(orders.getUser().getUserId())
                            .userName(orders.getUser().getUsername())
                            .userPhoneNumber(orders.getUser().getUserPhoneNumber())
                            .userAddr(orders.getUser().getUserAddr())
                            .build())
                    .getReadOrdersProductRes(GetReadOrdersProductRes.builder()
                            .productIdx(orders.getProduct().getProductIdx())
                            .brandName(orders.getProduct().getBrand().getBrandName())
                            .productName(orders.getProduct().getProductName())
                            .price(orders.getProduct().getPrice())
                            .build())
                    .build();

            return response;
        } else {
            return null;
        }
    }

    // ---------------여기부터 결제 기능---------------
    public String getToken() throws IOException {
        HttpsURLConnection conn = null;

        URL url = new URL("https://api.iamport.kr/users/getToken");
        conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);

        JsonObject json = new JsonObject();
        json.addProperty("imp_key", "7765128628442384");
        json.addProperty("imp_secret", "IxcluaywOlF5b0TL98DOqRsi49FVaDx7IGly2qAVz9jPy2okG8V7SKwi2JvauY0DOmsxZGl7Psbtq3zr");

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        bw.write(json.toString());
        bw.flush();
        bw.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        Gson gson = new Gson();
        String response = gson.fromJson(br.readLine(), Map.class).get("response").toString();

        String token = gson.fromJson(response, Map.class).get("access_token").toString();
        br.close();
        conn.disconnect();

        return token;
    }

    public Map<String, String> getPaymentInfo(String impUid) throws IOException {
        String token = getToken();
        HttpsURLConnection conn = null;

        URL url = new URL("https://api.iamport.kr/payments/" + impUid);
        conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", token);
        conn.setDoOutput(true);

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        Gson gson = new Gson();
        String response = gson.fromJson(br.readLine(), Map.class).get("response").toString();

        //System.out.println(response);
        br.close();
        conn.disconnect();

        String amount = response.split("amount")[1].split(",")[0].replace("=", "");
        //System.out.println(amount);
        String name = response.split(" name")[1].split(",")[0].replace("=", "");
        //System.out.println(name);
        String custom_data = response.split(" custom_data")[1].split(", customer_uid")[0].replace("=", "");
        //System.out.println(custom_data);

        Map<String, String> result = new HashMap<>();
        result.put("name", name);
        result.put("amount", amount);
        result.put("custom_data", custom_data);

        return result;

    }

    public String paymentValidation(String impUid) throws IOException {

        Map<String, String> paymentResult = getPaymentInfo(impUid);

        System.out.println(paymentResult.get("custom_data"));
        String custom_data = paymentResult.get("custom_data");
        Gson gson = new Gson();

        Integer productAmount = Integer.valueOf(Integer.valueOf(paymentResult.get("amount").split("\\.")[0]));
        Integer realAmount = 0;

        List<Map<String, Object>> orderInfo = gson.fromJson(custom_data, List.class);
        //System.out.println(orderInfo);
        //System.out.println(orderInfo.get(0));
        //System.out.println(orderInfo.get(1));
        for(int i=0; i<orderInfo.size(); i++) {
            String productName = (String)orderInfo.get(i).get("name");

            Double productPrice = ((Double)orderInfo.get(i).get("price")); // Double 형
            Integer resultPrice = productPrice.intValue();  // Double 형을 Integer 로 변경

            Optional<Product> result = productRepository.findByProductName(productName);
            Product product = result.get();

            if(resultPrice.equals(product.getPrice())) {
                realAmount += product.getPrice();
            }
        }

        if(productAmount.equals(realAmount)) {
            return "ok";
        } else {
            String token = getToken();
            cancelPayment(token, impUid, productAmount);
            return "error";
        }
    }

    // 환불처리
    public void cancelPayment(String token, String impUid, Integer amount) throws IOException {
        HttpsURLConnection conn = null;

        URL url = new URL("https://api.iamport.kr/payments/cancel");
        conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-type", "application/json");
        conn.setRequestProperty("Authorization", token);
        conn.setDoOutput(true);  // 출력 스트림 사용

        // 요청할 json 데이터
        JsonObject json = new JsonObject();
        json.addProperty("imp_uid", impUid);
        json.addProperty("amount", amount);
        json.addProperty("reason", "결제 금액 에러");

        // json 데이터를 서버로 전송
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        bw.write(json.toString());
        bw.flush();
        bw.close();

//        // 서버로부터 응답 json 데이터를 전달받음
//        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
//        Gson gson = new Gson();
//        String response = gson.fromJson(br.readLine(), Map.class).get("response").toString();
//
//        System.out.println(response);
//        br.close();
//        conn.disconnect();
    }
    // ---------------여기까지 결제 기능---------------
}
