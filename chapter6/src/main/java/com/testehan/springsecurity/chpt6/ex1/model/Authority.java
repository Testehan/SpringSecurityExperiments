package com.testehan.springsecurity.chpt6.ex1.model;

import jakarta.persistence.*;

@Entity
@Table (name ="authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    // Omitted getters and setters

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }
}
