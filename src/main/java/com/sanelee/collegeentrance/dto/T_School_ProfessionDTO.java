package com.sanelee.collegeentrance.dto;

import com.sanelee.collegeentrance.model.Profession;
import com.sanelee.collegeentrance.model.School;
import lombok.Data;

@Data
public class T_School_ProfessionDTO {
    private int scid;
    private int maxscore;
    private int avgscore;
    private int minscore;
    private int minrank;
    private int pid;
    private int sort;
    private Profession profession;
    private School school;
}

