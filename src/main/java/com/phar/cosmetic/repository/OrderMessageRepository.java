package com.phar.cosmetic.repository;

import com.phar.cosmetic.model.OrderMesage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMessageRepository extends JpaRepository<OrderMesage,Long> {
}
