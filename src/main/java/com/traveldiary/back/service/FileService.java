package com.traveldiary.back.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


public interface FileService {
    String postUpload(MultipartFile file);
    Resource getFile (String fileName);
}
