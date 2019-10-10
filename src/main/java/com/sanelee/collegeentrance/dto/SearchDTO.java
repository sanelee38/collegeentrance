package com.sanelee.collegeentrance.dto;

import lombok.Data;

import java.util.List;

@Data
public class SearchDTO {
    private List<SchoolDTO> schools;
    private List<ProfessionDTO> professions;
}
