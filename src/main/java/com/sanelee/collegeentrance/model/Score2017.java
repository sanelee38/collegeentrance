package com.sanelee.collegeentrance.model;

import lombok.Data;

@Data
public class Score {
    private Integer scid;
    private Integer aid;
    private String area;
    private Integer maxscore;
    private Integer avgscore;
    private Integer minscore;
    private Integer minrank;
    private Integer sid;
}
