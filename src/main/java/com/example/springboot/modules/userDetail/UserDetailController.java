package com.example.springboot.modules.userDetail;

import com.example.springboot.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/userdetail")
public class UserDetailController {

    @Autowired
    private UserDetailService userDetailService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getUserDetail(@PathVariable Long id) {
        UserDetailDto userDetail = userDetailService.getUserDetail(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(userDetail);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
