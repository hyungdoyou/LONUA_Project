package com.example.lonua.brand.controller;

import com.example.lonua.brand.model.request.PatchUpdateBrandReq;
import com.example.lonua.brand.model.request.PostRegisterBrandReq;
import com.example.lonua.brand.service.BrandService;
import com.example.lonua.config.BaseRes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/brand")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity register(
            @RequestPart(value = "brand") PostRegisterBrandReq postRegisterBrandReq,
            @RequestPart(value = "brandImage") MultipartFile brandFile) {
        BaseRes baseRes = brandService.register(postRegisterBrandReq, brandFile);

        return ResponseEntity.ok().body(baseRes);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list/{page}/{size}")
    public ResponseEntity list(@PathVariable Integer page, @PathVariable Integer size) {
        BaseRes baseRes = brandService.list(page, size);

        return ResponseEntity.ok().body(baseRes);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{idx}")
    public ResponseEntity read(@PathVariable Integer idx) {
        BaseRes baseRes = brandService.read(idx);

        return ResponseEntity.ok().body(baseRes);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity update(
            @RequestPart(value = "brand")PatchUpdateBrandReq patchUpdateBrandReq,
            @RequestPart(value = "brandImage") MultipartFile brandFile) {

        BaseRes baseRes = brandService.update(patchUpdateBrandReq, brandFile);
        return ResponseEntity.ok().body(baseRes);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{idx}")
    public ResponseEntity delete(@PathVariable Integer idx) {

        BaseRes baseRes = brandService.delete(idx);
        return ResponseEntity.ok().body(baseRes);
    }
}
