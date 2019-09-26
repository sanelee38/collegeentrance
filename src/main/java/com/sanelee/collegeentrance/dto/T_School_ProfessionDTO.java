package com.sanelee.collegeentrance.dto;

import com.sanelee.collegeentrance.model.Profession;
import com.sanelee.collegeentrance.model.School;
import lombok.Data;

@Data
public class T_School_ProfessionDTO {
    private Integer scid;
    private Integer maxscore;
    private Integer avgscore;
    private Integer minscore;
    private Integer minrank;
    private Integer pid;
    private Profession profession;
    private School school;
}

