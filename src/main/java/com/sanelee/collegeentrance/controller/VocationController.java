package com.sanelee.collegeentrance.controller;


import com.sanelee.collegeentrance.mapper.QuestionMapper;
import com.sanelee.collegeentrance.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class VocationController {
    @Autowired
    private QuestionMapper questionMapper;

    @RequestMapping("/vocation")
    public String vocation(Model model){
        List<Question> itemList = questionMapper.list();
        model.addAttribute("question",itemList);
        return "vocation";
    }
}
