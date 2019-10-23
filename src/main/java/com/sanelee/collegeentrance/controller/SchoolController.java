package com.sanelee.collegeentrance.controller;


import com.sanelee.collegeentrance.dto.SearchDTO;
import com.sanelee.collegeentrance.mapper.AreaMapper;
import com.sanelee.collegeentrance.mapper.ProfessionMapper;
import com.sanelee.collegeentrance.mapper.SchoolMapper;
import com.sanelee.collegeentrance.model.Area;
import com.sanelee.collegeentrance.model.Profession;
import com.sanelee.collegeentrance.model.School;
import com.sanelee.collegeentrance.service.SchoolService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SchoolController {

    @Autowired
    private SchoolMapper schoolMapper;
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private ProfessionMapper professionMapper;

    @GetMapping("/school")
    public String school(Model model){

        List<Area> areaList = areaMapper.list();
        List<School> schoolList = schoolMapper.list();
        List<School> schoolListBj = schoolMapper.listBJ();
        List<School> schoolListCq = schoolMapper.listCQ();
        List<School> schoolListSx = schoolMapper.listSX();
        List<School> schoolListHn = schoolMapper.listHN();
        List<School> schoolListYn = schoolMapper.listYN();
        List<School> schoolListHb = schoolMapper.listHB();
        List<School> schoolListHlj = schoolMapper.listHLJ();
        model.addAttribute("areas",areaList);
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

    @GetMapping("/schoolSearch")
    public String schoolSearch(HttpServletRequest request,
                               Model model,
                               @RequestParam(name = "search",required = false) String search,
                               @RequestParam(name = "select",required = false) String select,
                               @RequestParam(name = "proSearch",required = false) String proSearch){

        SearchDTO selects = new SearchDTO();
        SearchDTO proSearchs = new SearchDTO();
        if (StringUtils.isNotBlank(select)&&StringUtils.isNotBlank(proSearch)){
            selects.setSchools(null);
            selects.setProfessions(null);
            proSearchs = schoolService.proSearchsList(proSearch,select);
        }
        else if (StringUtils.isNotBlank(select)&&StringUtils.isBlank(proSearch)){
            selects = schoolService.selectList(select);
        }
//        else if (StringUtils.isBlank(select)&&StringUtils.isNotBlank(proSearch)){
//            selects.setSchools(null);
//            selects.setProfessions(null);
//            proSearchs = schoolService.proSearchList(proSearch);
//        }
        model.addAttribute("selectss",selects);
        model.addAttribute("proSearchss",proSearchs);
        SearchDTO searchs = schoolService.list(search);
        model.addAttribute("searchss",searchs);
        List<Area> areaList = areaMapper.list();
        model.addAttribute("areas",areaList);
        List<Profession> professionList = professionMapper.list();
        model.addAttribute("professions",professionList);
        return "schoolSearch";
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
