package com.sanelee.collegeentrance.service;

import com.sanelee.collegeentrance.dto.SchoolDTO;
import com.sanelee.collegeentrance.dto.SchoolQueryDTO;
import com.sanelee.collegeentrance.dto.SearchDTO;
import com.sanelee.collegeentrance.mapper.SchoolMapper;
import com.sanelee.collegeentrance.model.School;
import org.apache.commons.lang3.StringUtils;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
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
    public SearchDTO selectList(String select){
        if(StringUtils.isNotBlank(select)){
            String[] tags = StringUtils.split(select,",");
            select = Arrays.stream(tags).collect(Collectors.joining("|"));
        }
        SearchDTO searchDTO = new SearchDTO();
        SchoolQueryDTO schoolQueryDTO = new SchoolQueryDTO();
        schoolQueryDTO.setSelect(select);
        List<School> schools = schoolMapper.selectBySelect(schoolQueryDTO);
        List<SchoolDTO> schoolDTOList = new ArrayList<>();
        for (School school : schools){
            SchoolDTO schoolDTO = new SchoolDTO();
            BeanUtils.copyProperties(school,schoolDTO);
            schoolDTOList.add(schoolDTO);
        }
        searchDTO.setSchools(schoolDTOList);
        return searchDTO;
    }

    public SearchDTO proSearchList(String proSearch){
        if (StringUtils.isNotBlank(proSearch)){
            String[] tags = StringUtils.split(proSearch," ");
            proSearch = Arrays.stream(tags).collect(Collectors.joining("|"));
        }
        SearchDTO searchDTO = new SearchDTO();
        SchoolQueryDTO schoolQueryDTO = new SchoolQueryDTO();
        schoolQueryDTO.setProSearch(proSearch);
        List<School> schools = schoolMapper.selectByProSearch(schoolQueryDTO);
        List<SchoolDTO> schoolDTOList = new ArrayList<>();
        for (School school:schools){
            SchoolDTO schoolDTO = new SchoolDTO();
            BeanUtils.copyProperties(school,schoolDTO);
            schoolDTOList.add(schoolDTO);
        }
        searchDTO.setSchools(schoolDTOList);
        return searchDTO;
    }

    public SearchDTO proSearchsList(String proSearch,String select) {
        if(StringUtils.isNotBlank(proSearch)){
            String[] tags = StringUtils.split(proSearch," ");
            proSearch = Arrays.stream(tags).collect(Collectors.joining("|"));
            String[] tags2 = StringUtils.split(select,",");
            select = Arrays.stream(tags2).collect(Collectors.joining("|"));
        }

        SearchDTO searchDTO = new SearchDTO();
        SchoolQueryDTO schoolQueryDTO = new SchoolQueryDTO();
        schoolQueryDTO.setProSearch(proSearch);
        schoolQueryDTO.setSelect(select);
        List<School> schools = schoolMapper.selectByProSelect(schoolQueryDTO);
        List<SchoolDTO> schoolDTOList = new ArrayList<>();
        for (School school : schools){
            SchoolDTO schoolDTO = new SchoolDTO();
            BeanUtils.copyProperties(school,schoolDTO);
            schoolDTOList.add(schoolDTO);
        }
        searchDTO.setSchools(schoolDTOList);
        return searchDTO;
    }

    public List<SchoolDTO> schoolinfor(String proSearch,String select){
        if(StringUtils.isNotBlank(proSearch)){
            String[] tags = StringUtils.split(proSearch," ");
            proSearch = Arrays.stream(tags).collect(Collectors.joining("|"));
            String[] tags2 = StringUtils.split(select,",");
            select = Arrays.stream(tags2).collect(Collectors.joining("|"));
        }
        SchoolQueryDTO schoolQueryDTO = new SchoolQueryDTO();
        schoolQueryDTO.setProSearch(proSearch);
        schoolQueryDTO.setSelect(select);
        List<SchoolDTO> schoolDTOList = schoolMapper.selectByPrinter(schoolQueryDTO);
        return schoolDTOList;
    }
}
