package com.example.springboot.modules.auth;

import com.example.springboot.dto.ApiResponse;
import com.example.springboot.modules.auth.dto.AuthDTO;
import com.example.springboot.modules.auth.dto.AuthUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/auth", consumes = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody AuthUserDTO loginRequest) {
        AuthDTO authDTO = new AuthDTO();
        ApiResponse apiResponse = new ApiResponse();
        try {
            String loginToken = authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
            authDTO.setToken(loginToken);
            apiResponse.setMessage("success");
            apiResponse.setData(authDTO);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (AuthenticationException e) {
            apiResponse.setMessage("Incorrect username or password");
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/getusername")
    public ResponseEntity<AuthUserDTO> getUserName(@RequestBody AuthDTO userRequest) {
        AuthUserDTO authUserDTO = new AuthUserDTO();
        String username = authService.extractUserName(userRequest.getToken());
        authUserDTO.setUsername(username);
        return new ResponseEntity<>(authUserDTO, HttpStatus.OK);
    }
}
