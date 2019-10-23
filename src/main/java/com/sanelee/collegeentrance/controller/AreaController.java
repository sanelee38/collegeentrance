package com.sanelee.collegeentrance.controller;

import com.sanelee.collegeentrance.mapper.AreaMapper;
import com.sanelee.collegeentrance.model.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AreaController {

//    @Autowired
//    private AreaMapper areaMapper;
//    @GetMapping("/schoolSearch")
//    public String area(Model model){
//        List<Area> areaList = areaMapper.list();
//        model.addAttribute("areas",areaList);
//        return "schoolSearch";
//    }
}
