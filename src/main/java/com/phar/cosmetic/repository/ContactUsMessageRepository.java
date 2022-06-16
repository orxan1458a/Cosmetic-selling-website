package com.phar.cosmetic.repository;

import com.phar.cosmetic.model.ContactUsMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactUsMessageRepository extends JpaRepository<ContactUsMessage,Long> {
}
