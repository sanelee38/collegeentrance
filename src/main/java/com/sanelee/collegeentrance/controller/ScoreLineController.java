package com.sanelee.collegeentrance.controller;

import com.sanelee.collegeentrance.mapper.ScoreLineMapper;
import com.sanelee.collegeentrance.model.ScoreLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ScoreLineController {
    @Autowired
    private ScoreLineMapper scoreLineMapper;
    @GetMapping("/scoreline")
    public String ScoreLine(Model model){
        List<ScoreLine> scorelineList = scoreLineMapper.list();
        model.addAttribute("scoresLines",scorelineList);
        return("scoreline");
    }
}
