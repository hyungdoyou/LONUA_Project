package com.example.lonua.Seller.controller;

import com.example.lonua.Seller.model.request.PatchUpdateSellerReq;
import com.example.lonua.Seller.model.request.PostLoginSellerReq;
import com.example.lonua.Seller.model.request.PostSignupSellerReq;
import com.example.lonua.Seller.service.SellerService;
import com.example.lonua.brand.model.request.PatchUpdateBrandReq;
import com.example.lonua.common.BaseRes;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seller")
@Api(value="판매자 계정 컨트롤러 v1", tags="판매자 계정 API")
public class SellerController {

    private final SellerService sellerService;
    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity signup(@RequestBody @Valid PostSignupSellerReq postSignupSellerReq) {
        BaseRes baseRes = sellerService.signup(postSignupSellerReq);

        return ResponseEntity.ok().body(baseRes);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity signup(@RequestBody @Valid PostLoginSellerReq postLoginSellerReq) {
        BaseRes baseRes = sellerService.login(postLoginSellerReq);

        return ResponseEntity.ok().body(baseRes);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list/{page}/{size}")
    public ResponseEntity list(@PathVariable @NotNull @Positive Integer page, @PathVariable @NotNull @Positive Integer size) {
        BaseRes baseRes = sellerService.list(page, size);

        return ResponseEntity.ok().body(baseRes);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity update(@RequestBody PatchUpdateSellerReq patchUpdateSellerReq) {
        BaseRes baseRes = sellerService.update(patchUpdateSellerReq);

        return ResponseEntity.ok().body(baseRes);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{idx}")
    public ResponseEntity delete(@PathVariable @NotNull @Positive Integer idx) {
        BaseRes baseRes = sellerService.delete(idx);

        return ResponseEntity.ok().body(baseRes);
    }
}
