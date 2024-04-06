package com.financesbucket.financialservicemanage.infrastructure.adapters.email.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Username'")
    private String username;

    @Column(name = "email", nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Email'")
    private String email;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Long userId;

    // Constructor
    public User() {
    }

    public User(String username, String email, Long userId) {
        this.username = username;
        this.email = email;
        this.userId = userId;
    }
}