package com.sanelee.collegeentrance.dto;

import com.sanelee.collegeentrance.model.Profession;
import lombok.Data;

import java.util.List;

@Data
public class SchoolDTO {
    private Integer scid;
    private String name;
    private String areaname;
    private Integer areaid;
    private String batch;
    private char description;
    private String acronym;
    private Profession pid;

    private List<Profession> professions;
}
