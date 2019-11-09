package com.sanelee.collegeentrance.model;

import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;
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
    private String usedname;
    private String type;
    private Integer foundingYear;
    private String department;
    private String iscombine;
    private Integer is985;
    private Integer is211;
    private String isDoubleFirstClass;
    private Integer firstClassNum;
    private Integer facultyNum;
    private Integer academicianNum;
    private Integer changjiangScholarNum;
    private Integer teachersNum;
    private Integer undergraProNum;
    private Integer postgraProNum;
    private Integer doctorProNum;
    private Integer mainLabNum;
    private Integer undergraEnrollNum;
    private Integer postgraEnrollNum;
    private String schoolWeb;




}
