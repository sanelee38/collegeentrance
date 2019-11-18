package com.sanelee.collegeentrance.model;

import lombok.Data;

@Data
public class Gaokao {
    private int sid;
    private int average;
    private int year;
    private int min;
    private int school_id;
    private int min_section;
    private int max;
    private String local_province_name;
    private String local_batch_name;
    private String spname;
    private String name;
    private String local_type_name;
    private int page;
    }
