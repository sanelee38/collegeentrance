package com.sanelee.collegeentrance.controller;

import com.sanelee.collegeentrance.dto.ScoreDTO;
import com.sanelee.collegeentrance.mapper.ProfessionMapper;
import com.sanelee.collegeentrance.mapper.SchoolMapper;
import com.sanelee.collegeentrance.model.Profession;
import com.sanelee.collegeentrance.model.School;
import com.sanelee.collegeentrance.model.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProfessionController {

    @Autowired
    private ProfessionMapper professionMapper;
    @Autowired
    private SchoolMapper schoolMapper;
    @GetMapping("/profession")
    public String profession(Model model){
        List<Profession> professionList = professionMapper.list();
        model.addAttribute("professions",professionList);
        return "profession";
    }

    @GetMapping("/professionSC/{pid}")
    public String professionSC(@PathVariable(name="pid") Integer pid,
                               Model model) {
        List<School> schoolList = schoolMapper.findByPid(pid);
        model.addAttribute("schools", schoolList);
        return "professionSC";
    }
}
