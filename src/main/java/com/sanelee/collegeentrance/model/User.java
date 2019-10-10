package com.sanelee.collegeentrance.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name="table_user")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
}
