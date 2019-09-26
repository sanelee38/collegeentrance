package com.sanelee.collegeentrance.dto;

import com.sanelee.collegeentrance.model.Area;
import com.sanelee.collegeentrance.model.School;
import lombok.Data;

@Data
public class ScoreDTO {
    private Integer scid;
    private Integer aid;
    private String area;
    private Integer maxscore;
    private Integer avgscore;
    private Integer minscore;
    private Integer minrank;
    private Integer sid;
    private School school;
}
