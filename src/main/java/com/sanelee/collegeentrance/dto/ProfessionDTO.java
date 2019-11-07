package com.sanelee.collegeentrance.dto;

import com.sanelee.collegeentrance.model.School;
import com.sanelee.collegeentrance.model.T_School_Profession;
import lombok.Data;

import java.util.List;

@Data
public class ProfessionDTO {
    private Integer pid;
    private String proname;
    private String object;
    private Integer obid;
    private School scid;
    private String pfdescription;
//    private T_School_Profession maxscore;
//    private T_School_Profession avgscore;
//    private T_School_Profession minscore;
//    private T_School_Profession minrank;

    private List<School> schools;

}
