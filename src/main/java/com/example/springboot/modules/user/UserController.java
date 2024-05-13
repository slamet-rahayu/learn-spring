package com.example.springboot.modules.user;

import com.example.springboot.dto.ApiResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserService userService;

    @GetMapping("/test-user")
    public String testUser() {
        try {
            entityManager.createQuery("select count(id) as total from users", Long.class).getSingleResult();
            return "Success";
        } catch (Exception e) {
            return "failed: "+e.getMessage();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody UserEntity user) {
        userService.register(user);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Register Success");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
