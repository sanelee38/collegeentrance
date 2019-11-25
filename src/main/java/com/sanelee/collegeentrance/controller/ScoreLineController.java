package com.sanelee.collegeentrance.controller;

import com.sanelee.collegeentrance.model.Gaokao;
import com.sanelee.collegeentrance.service.GaokaoService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class ScoreLineController {
    @Autowired
    private GaokaoService gaokaoService;

    @RequestMapping("/scoreline")
    public String register(){
        return "scoreline";
    }
    @RequestMapping("/gaokaoquery")
    public String gaokaoquery(){
        return "gaokaoquery";
    }


    @GetMapping("/major")
    public String major(@RequestParam(name="score",required = false) Integer score,
                        @RequestParam(name="province",required = false) String province,
                        @RequestParam(name="object",required = false) String object,
                        @RequestParam(name="direction",required = false) String direction,Model model) {
        List<Gaokao> gaokaoList = gaokaoService.gaokaoQuery(score, province, object, direction);
        model.addAttribute("gaokao", gaokaoList);

        return "major";
    }

}
