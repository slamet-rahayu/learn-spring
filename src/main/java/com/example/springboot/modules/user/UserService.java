package com.example.springboot.modules.user;

import com.example.springboot.modules.userDetail.UserDetailEntity;
import com.example.springboot.modules.userDetail.UserDetailRepository;
import com.example.springboot.validation.UniqueConstraintValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UniqueConstraintValidator {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean isUnique(String value) {
        return userRepository.existsByUsername(value);
    }

    @Transactional
    public void register(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserDetailEntity userDetail = user.getUserDetail();
        if (userDetail != null) {
            userDetailRepository.save(userDetail);
        }
        userRepository.save(user);
    }

}
