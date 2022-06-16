package com.phar.cosmetic.service;

import com.phar.cosmetic.model.OrderMesage;
import org.hibernate.criterion.Order;

import java.util.List;

public interface OrderMessageService {
    void saveDB(String name,String surName,String phone,String address,int count,Long productId);
    List<OrderMesage> findAll();
    OrderMesage findById(Long id);
    void save(OrderMesage orderMesage);
}
