package com.example.springboot.modules.user;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
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
    public UserEntity register(@RequestBody UserEntity user) {
        return userService.register(user);
    }
}
