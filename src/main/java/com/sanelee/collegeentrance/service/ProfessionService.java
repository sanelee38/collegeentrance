package com.sanelee.collegeentrance.service;

import com.sanelee.collegeentrance.dto.T_School_ProfessionDTO;
import com.sanelee.collegeentrance.mapper.SchoolMapper;
import com.sanelee.collegeentrance.mapper.T_School_ProfessionMapper;
import com.sanelee.collegeentrance.model.School;
import com.sanelee.collegeentrance.model.T_School_Profession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProfessionService {
    @Autowired
    private T_School_ProfessionMapper t_school_professionMapper;

    @Autowired
    private SchoolMapper schoolMapper;

    public List<T_School_ProfessionDTO> findScoreByScid (Integer scid){
        List<T_School_Profession> t_school_professions=t_school_professionMapper.list(scid);
        List<T_School_ProfessionDTO> t_school_professionDTOList = new ArrayList<>();
        for (T_School_Profession t_school_profession:t_school_professions){
            School school = schoolMapper.findByScid(t_school_profession.getScid());
            T_School_ProfessionDTO t_school_professionDTO = new T_School_ProfessionDTO();
            BeanUtils.copyProperties(t_school_profession,t_school_professionDTO);
            t_school_professionDTO.setSchool(school);
            t_school_professionDTOList.add(t_school_professionDTO);
        }
        return t_school_professionDTOList;
    }
}
