package com.sanelee.collegeentrance.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Profession {
    private Integer pid;
    private String proname;
    private Integer maxscore;
    private Integer avgscore;
    private Integer minscore;
    private Integer minrank;

}
