package com.sanelee.collegeentrance.controller;

import com.sanelee.collegeentrance.mapper.ScoreLineMapper;
import com.sanelee.collegeentrance.model.ScoreLine;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ScoreLineController {
    @RequestMapping("/scoreline")
    public String register(){
        return "scoreline";
    }
}
