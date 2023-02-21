package com.example.projectqlch.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "token")
@Data
public class SignUpToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String token;
}
