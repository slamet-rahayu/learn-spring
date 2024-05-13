package com.example.springboot.modules.user;


import com.example.springboot.annotations.Unique;
import com.example.springboot.validation.UniqueConstraintValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "users")
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
//    @Unique(service = UniqueConstraintValidator.class)
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @NotBlank
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @Column(name = "role", nullable = false)
    private String role;

    // Constructors
//    public UserEntity(String username, String password, String name, String role) {
//        this.username = username;
//        this.password = password;
//        this.name = name;
//        this.role = role;
//    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
