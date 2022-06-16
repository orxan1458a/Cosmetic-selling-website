package com.phar.cosmetic.service.imp;

import com.phar.cosmetic.PasswordHasher;
import com.phar.cosmetic.model.User;
import com.phar.cosmetic.repository.UserRepository;
import com.phar.cosmetic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public void saveDB() {
        String password="parviz";
        userRepository.deleteAll();
        User user=new User();
        user.setUserName("parviz");
        user.setFirstName("Pərviz");
        user.setLastName("İmanlı");
        user.setEmail("parviz.imanv@gmail.com");
        user.setGender("kişi");
        String hashPassword = PasswordHasher.getMd5("triaqoparviz");
        user.setPassword(password);
        user.setConfirmPassword(password);
        user.setRoles("Admin");
        user.setActive(true);
        userRepository.save(user);

    }
}
