package com.phar.cosmetic.controller;

import com.phar.cosmetic.model.Cosmetic;
import com.phar.cosmetic.service.CosmeticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    CosmeticService cosmeticService;
    @GetMapping("/all")
    public List<Cosmetic> all(){
        return cosmeticService.findAll();
    }
}
