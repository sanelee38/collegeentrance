package com.sanelee.collegeentrance.service;


import com.sanelee.collegeentrance.dto.Score2017DTO;
import com.sanelee.collegeentrance.dto.ScoreDTO;
import com.sanelee.collegeentrance.mapper.AreaMapper;
import com.sanelee.collegeentrance.mapper.SchoolMapper;
import com.sanelee.collegeentrance.mapper.Score2017Mapper;
import com.sanelee.collegeentrance.mapper.ScoreMapper;
import com.sanelee.collegeentrance.model.School;
import com.sanelee.collegeentrance.model.Score;
import com.sanelee.collegeentrance.model.Score2017;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Score2017Service {

    @Autowired
    private Score2017Mapper score2017Mapper;

    @Autowired
    private AreaMapper areaMapper;

    @Autowired
    private SchoolMapper schoolMapper;

    public List<Score2017DTO> find2017ByScidL(Integer scid) {
       List<Score2017> score2017s = score2017Mapper.listL(scid);
       List<Score2017DTO> score2017DTOListL=new ArrayList<>();
        for (Score2017 score2017 : score2017s) {
            School school = schoolMapper.findByScid(score2017.getScid());
            Score2017DTO score2017DTO = new Score2017DTO();
            BeanUtils.copyProperties(score2017,score2017DTO);
            score2017DTO.setSchool(school);
            score2017DTOListL.add(score2017DTO);
        }
        return score2017DTOListL;
    }
    public List<Score2017DTO> find2017ByScidW(Integer scid) {
        List<Score2017> score2017s = score2017Mapper.listW(scid);
        List<Score2017DTO> score2017DTOListW=new ArrayList<>();
        for (Score2017 score2017 : score2017s) {
            School school = schoolMapper.findByScid(score2017.getScid());
            Score2017DTO score2017DTO = new Score2017DTO();
            BeanUtils.copyProperties(score2017,score2017DTO);
            score2017DTO.setSchool(school);
            score2017DTOListW.add(score2017DTO);
        }
        return score2017DTOListW;
    }
}
