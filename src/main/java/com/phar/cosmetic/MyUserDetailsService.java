package com.phar.cosmetic;

import com.phar.cosmetic.MyUserDetails;
import com.phar.cosmetic.model.User;
import com.phar.cosmetic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User>user= userRepository.findByUserName(userName);

        user.orElseThrow(()->new UsernameNotFoundException("Not Found : "+userName));

        return user.map(MyUserDetails::new).get();

    }
}
