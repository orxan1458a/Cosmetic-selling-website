package com.phar.cosmetic.service;

import com.phar.cosmetic.model.Cosmetic;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.util.List;
import java.util.Set;

public interface CosmeticService {
    void add(String productName, String brendName, Long cosmeticCode,Double firstPrice, Double price, String productAbout, String category,String subCategory, MultipartFile image);
    List<Cosmetic> findAll();
    Cosmetic findById(Long id);
    List<Cosmetic> findByCategory(String category);
    List<Cosmetic> findBySubCategory(String subCategory);
    void categoryController(Model mdoel, String category);
    void update(Long id,String name,String brandName,String category,Long cosmeticCode,Double price);
    void deleteById(Long id);
    List<Cosmetic> findByBrandName(String brandName);
    Set<String> brandNames();

}
