package com.sanelee.collegeentrance.controller;

import com.sanelee.collegeentrance.dto.ProfessionDTO;
import com.sanelee.collegeentrance.dto.Score2017DTO;
import com.sanelee.collegeentrance.dto.ScoreDTO;
import com.sanelee.collegeentrance.dto.T_School_ProfessionDTO;
import com.sanelee.collegeentrance.mapper.*;
import com.sanelee.collegeentrance.model.*;
import com.sanelee.collegeentrance.service.ProfessionService;
import com.sanelee.collegeentrance.service.Score2017Service;
import com.sanelee.collegeentrance.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class SchoolDescController {

    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private  SchoolMapper schoolMapper;
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private Score2017Mapper score2017Mapper;
    @Autowired
    private Score2017Service score2017Service;
    @Autowired
    private ProfessionMapper professionMapper;
    @Autowired
    private ProfessionService professionService;
    @Autowired
    private T_School_ProfessionMapper t_school_professionMapper;


    @RequestMapping("/school_desc_l/{scid}")
    public String schoolDesc_L(@PathVariable(name="scid") Integer scid,
                             Model model){
        School school = schoolMapper.findByScid(scid);
        List<ScoreDTO> scoreList = scoreService.findByScidL(scid);
        List<Score> scoresList = scoreMapper.list();
        List<ScoreDTO> scoreListW = scoreService.findByScidW(scid);
        List<Score> scoresListW = scoreMapper.list();
        List<Score2017DTO> score2017List = score2017Service.find2017ByScidL(scid);
        List<Score2017> scores2017List = score2017Mapper.list();
        List<Score2017DTO> score2017ListW = score2017Service.find2017ByScidW(scid);
        List<Score2017> scores2017ListW = score2017Mapper.list();
        List<Profession> professionList = professionMapper.findProByScid(scid);
        List<Profession> professionsList = professionMapper.list();
        model.addAttribute("schools",school);
        model.addAttribute("scores",scoresList);
        model.addAttribute("score",scoreList);
        model.addAttribute("scoresW",scoresListW);
        model.addAttribute("scoreW",scoreListW);
        model.addAttribute("scores2017",scores2017List);
        model.addAttribute("score2017",score2017List);
        model.addAttribute("scoresW2017",scores2017ListW);
        model.addAttribute("scoreW2017",score2017ListW);
        model.addAttribute("professions",professionsList);
        model.addAttribute("profession",professionList);
        return "school_desc_l";
    }

    @RequestMapping("/introduce/{scid}")
    public String schoolIntroduce(@PathVariable(name="scid") Integer scid,
                               Model model){
        School school = schoolMapper.findByScid(scid);
        model.addAttribute("schools",school);
        return "introduce";
    }

    @RequestMapping("/schoolScore/{scid}")
    public String schoolScore(@PathVariable(name="scid") Integer scid,
                                  Model model){
        School school = schoolMapper.findByScid(scid);
        List<ScoreDTO> scoreList = scoreService.findByScidL(scid);
        List<Score> scoresList = scoreMapper.list();
        List<ScoreDTO> scoreListW = scoreService.findByScidW(scid);
        List<Score> scoresListW = scoreMapper.list();
        List<Score2017DTO> score2017List = score2017Service.find2017ByScidL(scid);
        List<Score2017> scores2017List = score2017Mapper.list();
        List<Score2017DTO> score2017ListW = score2017Service.find2017ByScidW(scid);
        List<Score2017> scores2017ListW = score2017Mapper.list();
        model.addAttribute("schools",school);
        model.addAttribute("scores",scoresList);
        model.addAttribute("score",scoreList);
        model.addAttribute("scoresW",scoresListW);
        model.addAttribute("scoreW",scoreListW);
        model.addAttribute("scores2017",scores2017List);
        model.addAttribute("score2017",score2017List);
        model.addAttribute("scoresW2017",scores2017ListW);
        model.addAttribute("scoreW2017",score2017ListW);
        return "schoolScore";
    }

    @RequestMapping("/professionScore/{scid}")
    public String professionScore(@PathVariable(name="scid") Integer scid,
                                  Model model){
        School school = schoolMapper.findByScid(scid);
        List<Profession> professionList = professionMapper.findProByScid(scid);
        List<Profession> professionsList = professionMapper.list();
        model.addAttribute("schools",school);
        model.addAttribute("professions",professionsList);
        model.addAttribute("profession",professionList);
        return "professionScore";
    }




}
