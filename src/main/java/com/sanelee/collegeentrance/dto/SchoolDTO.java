package com.sanelee.collegeentrance.dto;

import com.sanelee.collegeentrance.model.Profession;
import com.sanelee.collegeentrance.model.T_School_Profession;
import lombok.Data;

import java.util.List;

@Data
public class SchoolDTO {
    private Integer scid;
    private String name;
    private String areaname;
    private Integer areaid;
    private String batch;
    private String description;
    private String acronym;
    private Profession pid;
    private String  proname;
    private String region;
    private Integer reid;
    private int maxscore;
    private int avgscore;
    private int minscore;
    private int minrank;
    private Profession profession;
    private T_School_Profession t_school_profession;

    private List<Profession> professions;
}
