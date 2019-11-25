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
    private String userRealname;
    private String userGender;
    private String userPhone;
    private String userWechat;
    private int userScore;
    private String userArea;
    private String userSort;
    private int userRank;
}
