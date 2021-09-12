package com.Hanium.CarCamping.controller.image;

import com.Hanium.CarCamping.config.security.jwt.JwtService;
import com.Hanium.CarCamping.domain.dto.response.Result;
import com.Hanium.CarCamping.service.Reponse.ResponseService;
import com.Hanium.CarCamping.service.S3Service.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ImageUploadController {
    private final S3Uploader s3Uploader;
    private final ResponseService responseService;
    private final JwtService jwtService;
    @PostMapping("/upload")
    public Result upload(@RequestParam("images") MultipartFile multipartFile, @RequestHeader("token")String token) throws IOException {
        jwtService.isUsable(token);
        return responseService.getSingleResult(s3Uploader.upload(multipartFile, "campsiteimage"));
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam("images")String  fileName, @RequestHeader("token")String token) throws  IOException{
        jwtService.isUsable(token);
        s3Uploader.delete(fileName,"campsiteimage");
        return responseService.getSuccessResult();
    }
}
