package com.archivingsystem.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username",nullable = false,unique = true)
    private String userName;
    @Column(name = "password",nullable = false)
    private String userPassword;

    public User(String userName,String userPassword) {
        this.userName=userName;
        this.userPassword=userPassword;
    }

}
