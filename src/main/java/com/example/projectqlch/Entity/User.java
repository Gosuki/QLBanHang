package com.example.projectqlch.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean active;
    private String imageUrl;
}
