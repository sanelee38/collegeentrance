package com.sanelee.collegeentrance.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class User {
    private Long id;
    private Integer admin;
    private String username;
    private String password;
    private String token;
    private Integer pay;
}
