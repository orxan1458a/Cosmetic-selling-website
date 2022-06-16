
package com.phar.cosmetic.repository;

import com.phar.cosmetic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
   // User findByUserName(String userName);
    Optional<User> findByUserName(String userName);
}