package com.sanelee.collegeentrance.service;

import com.sanelee.collegeentrance.dto.SchoolDTO;
import com.sanelee.collegeentrance.dto.SchoolQueryDTO;
import com.sanelee.collegeentrance.dto.SearchDTO;
import com.sanelee.collegeentrance.mapper.SchoolMapper;
import com.sanelee.collegeentrance.model.School;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {
    @Autowired
    private SchoolMapper schoolMapper;

    public SearchDTO list(String search){
        if(StringUtils.isNotBlank(search)){
            String[] tags = StringUtils.split(search," ");
            search = Arrays.stream(tags).collect(Collectors.joining("|"));
        }
        SearchDTO searchDTO = new SearchDTO();
        SchoolQueryDTO schoolQueryDTO = new SchoolQueryDTO();
        schoolQueryDTO.setSearch(search);
        List<School> schools = schoolMapper.selectBySearch(schoolQueryDTO);
        List<SchoolDTO> schoolDTOList = new ArrayList<>();
        for (School school : schools){
            SchoolDTO schoolDTO = new SchoolDTO();
            BeanUtils.copyProperties(school,schoolDTO);
            schoolDTOList.add(schoolDTO);
        }
        searchDTO.setSchools(schoolDTOList);
        return searchDTO;

    }

}
