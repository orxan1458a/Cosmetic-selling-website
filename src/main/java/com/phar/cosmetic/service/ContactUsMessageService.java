package com.phar.cosmetic.service;

import com.phar.cosmetic.model.ContactUsMessage;

import java.util.List;

public interface ContactUsMessageService {
    void saveDB(String name,String surName,String email,String message);
    List<ContactUsMessage> findAll();
    void sendMessage(Long id,String message);
    ContactUsMessage findById(Long id);
}
