package com.phar.cosmetic.service;

import com.phar.cosmetic.model.Information;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface InformationService {
    void addInformation(String title, String content, String url, MultipartFile image);
    String stringSplit(String content);
    List<Information> findAll();
    Information findById(Long id);
}
