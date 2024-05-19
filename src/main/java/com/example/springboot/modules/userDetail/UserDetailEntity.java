package com.example.springboot.modules.userDetail;

import com.example.springboot.modules.user.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "user_detail")
@Table(name = "user_detail")
public class UserDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "fullname")
    private String fullname;

    @NotBlank
    @Column(name = "email")
    private String email;

    @NotBlank
    @Column(name = "mobile")
    private String mobile;

    @NotBlank
    @Column(name = "city")
    private String city;

    @NotBlank
    @Column(name = "bio")
    private String bio;

    @OneToOne(mappedBy = "userDetail")
    private UserEntity user;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
