package br.com.sabrina.sgt.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileStorageProperties {
    @Value("${path.upload}")
    private String uploadDir;

    public String getUploadDir() {
        return this.uploadDir;
    }
}