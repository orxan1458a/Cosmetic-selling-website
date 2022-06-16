package com.phar.cosmetic.service.imp;


import com.phar.cosmetic.EmailService;
import com.phar.cosmetic.model.ContactUsMessage;
import com.phar.cosmetic.repository.ContactUsMessageRepository;
import com.phar.cosmetic.service.ContactUsMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactUsMessageServiceImp implements ContactUsMessageService {
    @Autowired
    ContactUsMessageRepository contactUsMessageRepository;
    @Autowired
    EmailService emailService;


    @Override
    public void saveDB(String name, String surName, String email, String message) {
        ContactUsMessage contactUsMessage=new ContactUsMessage();
        contactUsMessage.setName(name);
        contactUsMessage.setSurName(surName);
        contactUsMessage.setEmail(email);
        contactUsMessage.setMessage(message);
        contactUsMessageRepository.save(contactUsMessage);



    }

    @Override
    public List<ContactUsMessage> findAll() {
        return contactUsMessageRepository.findAll();
    }

    @Override
    public void sendMessage(Long id, String message) {
        contactUsMessageRepository.findById(id).get().setAdminMessage(message);
        contactUsMessageRepository.save(contactUsMessageRepository.findById(id).get());
        emailService.adminMessageSender(id,message,contactUsMessageRepository.findById(id).get().getEmail());

    }

    @Override
    public ContactUsMessage findById(Long id) {
        return contactUsMessageRepository.findById(id).get();
    }


}
