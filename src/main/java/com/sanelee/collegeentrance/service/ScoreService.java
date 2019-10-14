package com.sanelee.collegeentrance.service;


import com.sanelee.collegeentrance.dto.*;
import com.sanelee.collegeentrance.mapper.AreaMapper;
import com.sanelee.collegeentrance.mapper.SchoolMapper;
import com.sanelee.collegeentrance.mapper.ScoreMapper;
import com.sanelee.collegeentrance.model.Area;
import com.sanelee.collegeentrance.model.Profession;
import com.sanelee.collegeentrance.model.School;
import com.sanelee.collegeentrance.model.Score;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    @Autowired
    private AreaMapper areaMapper;

    @Autowired
    private SchoolMapper schoolMapper;

    public List<ScoreDTO> findByScidL(Integer scid) {
       List<Score> scores = scoreMapper.listL(scid);
       List<ScoreDTO> scoreDTOListL=new ArrayList<>();
        for (Score score : scores) {
            School school = schoolMapper.findByScid(score.getScid());
            ScoreDTO scoreDTO = new ScoreDTO();
            BeanUtils.copyProperties(score,scoreDTO);
            scoreDTO.setSchool(school);
            scoreDTOListL.add(scoreDTO);
        }
        return scoreDTOListL;
    }
    public List<ScoreDTO> findByScidW(Integer scid) {
        List<Score> scores = scoreMapper.listW(scid);
        List<ScoreDTO> scoreDTOListW=new ArrayList<>();
        for (Score score : scores) {
            School school = schoolMapper.findByScid(score.getScid());
            ScoreDTO scoreDTO = new ScoreDTO();
            BeanUtils.copyProperties(score,scoreDTO);
            scoreDTO.setSchool(school);
            scoreDTOListW.add(scoreDTO);
        }
        return scoreDTOListW;
    }

}
