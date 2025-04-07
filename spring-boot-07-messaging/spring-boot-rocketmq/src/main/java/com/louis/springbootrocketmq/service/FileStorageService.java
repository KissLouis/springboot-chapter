package com.louis.springbootrocketmq.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {
    public String store(MultipartFile file) {
        // 实现文件存储逻辑
        return "File uploaded: " + file.getOriginalFilename();
    }
}
