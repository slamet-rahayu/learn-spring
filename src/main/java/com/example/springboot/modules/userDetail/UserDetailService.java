package com.example.springboot.modules.userDetail;

import com.example.springboot.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserDetailService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    public UserDetailDto getUserDetail(Long id) {
        UserDetailEntity userDetail = userDetailRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Not Found!"));
        return mapToDTO(userDetail);
    }

    private UserDetailDto mapToDTO(UserDetailEntity userDetail) {
        UserDetailDto dto = new UserDetailDto();
        dto.setId(userDetail.getId());
        dto.setFullname(userDetail.getFullname());
        dto.setEmail(userDetail.getEmail());
        dto.setMobile(userDetail.getMobile());
        dto.setCity(userDetail.getCity());
        dto.setBio(userDetail.getBio());
        return dto;
    }
}
