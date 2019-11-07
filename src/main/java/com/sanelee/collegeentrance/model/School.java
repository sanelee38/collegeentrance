package com.sanelee.collegeentrance.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class School{
    private Integer scid;
    private String name;
    private String areaname;
    private Integer areaid;
    private String batch;
    private String description;
    private String acronym;
    private String region;
    private Integer reid;

}
