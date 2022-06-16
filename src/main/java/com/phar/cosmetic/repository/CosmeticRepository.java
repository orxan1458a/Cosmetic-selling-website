package com.phar.cosmetic.repository;

import com.phar.cosmetic.model.Cosmetic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CosmeticRepository extends JpaRepository<Cosmetic,Long> {
    List<Cosmetic> findByCategory(String category);
    List<Cosmetic> findBySubCategory(String subCategory);
    List<Cosmetic> findByBrandName(String brandName);
}
