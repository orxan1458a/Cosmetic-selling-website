package com.phar.cosmetic.service.imp;

import com.phar.cosmetic.model.OrderMesage;
import com.phar.cosmetic.repository.CosmeticRepository;
import com.phar.cosmetic.repository.OrderMessageRepository;
import com.phar.cosmetic.service.OrderMessageService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderMessageServiceImp implements OrderMessageService {
    @Autowired
    OrderMessageRepository orderMessageRepository;
    @Autowired
    CosmeticRepository cosmeticRepository;
    @Override
    public void saveDB(String name, String surName, String phone, String address, int count, Long cosmeticId) {
        String cosmeticName= cosmeticRepository.findById(cosmeticId).get().getName();
        Long cosmeticCode=cosmeticRepository.findById(cosmeticId).get().getCosmeticCode();
        double price=cosmeticRepository.findById(cosmeticId).get().getPrice();

        OrderMesage message=new OrderMesage();
        message.setName(name);
        message.setSurName(surName);
        message.setPhone(phone);
        message.setAddress(address);
        message.setCount(count);
        message.setCosmeticName(cosmeticName);
        message.setCosmeticId(cosmeticId);
        message.setCosmeticCode(cosmeticCode);
        message.setPrice(price);
        message.setTotalPrice(price*count);
        LocalDateTime localDateTime = LocalDateTime.now();
        message.setCurrentDateTime(localDateTime);
        orderMessageRepository.save(message);

    }

    @Override
    public List<OrderMesage> findAll() {
        return orderMessageRepository.findAll();
    }

    @Override
    public OrderMesage findById(Long id) {
        return orderMessageRepository.findById(id).get();
    }

    @Override
    public void save(OrderMesage orderMesage) {
        orderMessageRepository.save(orderMesage);
    }


}
