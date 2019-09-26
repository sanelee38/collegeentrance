package com.sanelee.collegeentrance.controller;


import com.sanelee.collegeentrance.mapper.SchoolMapper;
import com.sanelee.collegeentrance.model.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SchoolController {

    @Autowired
    private SchoolMapper schoolMapper;
    @GetMapping("/school")
    public String school(Model model,
                         @RequestParam(name = "search",required = false) String search){
        List<School> schoolList = schoolMapper.list();
        List<School> schoolListBj = schoolMapper.listBJ();
        List<School> schoolListCq = schoolMapper.listCQ();
        List<School> schoolListSx = schoolMapper.listSX();
        List<School> schoolListHn = schoolMapper.listHN();
        List<School> schoolListYn = schoolMapper.listYN();
        List<School> schoolListHb = schoolMapper.listHB();
        List<School> schoolListHlj = schoolMapper.listHLJ();
        model.addAttribute("schools",schoolList);
        model.addAttribute("schoolsBj",schoolListBj);
        model.addAttribute("schoolsCq",schoolListCq);
        model.addAttribute("schoolsSx",schoolListSx);
        model.addAttribute("schoolsHn",schoolListHn);
        model.addAttribute("schoolsYn",schoolListYn);
        model.addAttribute("schoolsHb",schoolListHb);
        model.addAttribute("schoolsHlj",schoolListHlj);
        return "school";
    }

    @GetMapping("/school_area_bj")
    public String schoolAreaBJ(Model model){
        List<School> schoolList = schoolMapper.listBJ();
        model.addAttribute("schools",schoolList);
        return "school_area_bj";
    }
//
//    @GetMapping("/school_area_cq")
//    public String schoolAreaCQ(Model model){
//        List<School> schoolList = schoolMapper.listCQ();
//        model.addAttribute("schools",schoolList);
//        return "school_area_cq";
//    }
//
//    @GetMapping("/school_area_hn")
//    public String schoolAreaHN(Model model){
//        List<School> schoolList = schoolMapper.listHN();
//        model.addAttribute("schools",schoolList);
//        return "school_area_hn";
//    }
//
//    @GetMapping("/school_area_yn")
//    public String schoolAreaYN(Model model){
//        List<School> schoolList = schoolMapper.listYN();
//        model.addAttribute("schools",schoolList);
//        return "school_area_yn";
//    }
//
//    @GetMapping("/school_area_sx")
//    public String schoolAreaSX(Model model){
//        List<School> schoolList = schoolMapper.listSX();
//        model.addAttribute("schools",schoolList);
//        return "school_area_sx";
//    }
//
//    @GetMapping("/school_area_hb")
//    public String schoolAreaHLJ(Model model){
//        List<School> schoolList = schoolMapper.listHB();
//        model.addAttribute("schools",schoolList);
//        return "school_area_hb";
//    }
//
//    @GetMapping("/school_area_hlj")
//    public String schoolAreaHB(Model model){
//        List<School> schoolList = schoolMapper.listHLJ();
//        model.addAttribute("schools",schoolList);
//        return "school_area_hlj";
//    }
}
