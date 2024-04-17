package com.example.springboot.modules.user;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/test-user")
    public String testUser() {
        try {
            entityManager.createQuery("select count(id) as total from users", Long.class).getSingleResult();
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed: "+e.getMessage();
        }
    }
}
