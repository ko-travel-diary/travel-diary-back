package com.traveldiary.back.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.traveldiary.back.service.FileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/traveldiary/v1/image")
@RequiredArgsConstructor
public class ImageUploadController {

    private final FileService fileService;

    @PostMapping("/upload")
    public String upload(
        @RequestParam("file") MultipartFile file
    ){
        String url = fileService.postUpload(file);
        return url;
    }

    @GetMapping(value = "/file/{fileName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public Resource getFile(
        @PathVariable("fileName") String fileName
    ){
        Resource resource = fileService.getFile(fileName);
        return resource;
    }

}