package com.sanelee.collegeentrance.model;

import lombok.Data;

@Data
public class Question {
    private int id;
    private String item;
    private int value1;
    private int value2;
}
