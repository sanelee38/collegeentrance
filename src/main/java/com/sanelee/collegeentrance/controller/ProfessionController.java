package com.sanelee.collegeentrance.controller;

import com.sanelee.collegeentrance.dto.ScoreDTO;
import com.sanelee.collegeentrance.dto.SearchDTO;
import com.sanelee.collegeentrance.mapper.ProfessionMapper;
import com.sanelee.collegeentrance.mapper.SchoolMapper;
import com.sanelee.collegeentrance.model.Profession;
import com.sanelee.collegeentrance.model.School;
import com.sanelee.collegeentrance.model.Score;
import com.sanelee.collegeentrance.service.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
public class ProfessionController {

    @Autowired
    private ProfessionMapper professionMapper;
    @Autowired
    private SchoolMapper schoolMapper;
    @Autowired
    private ProfessionService professionService;
    @GetMapping("/profession")
    public String profession(Model model){
        List<Profession> professionList = professionMapper.list();
        List<Profession> objectList = professionMapper.objectObid();
        model.addAttribute("professions",professionList);
        model.addAttribute("objects",objectList);
        return "profession";
    }

    @RequestMapping("/professionList/{obid}")
    public String professionList(@PathVariable(name = "obid") Integer obid,
                                 Model model){
        List<Profession> professionList = professionMapper.selectByObid(obid);
        model.addAttribute("professions",professionList);
        return "professionList";
    }

    @RequestMapping("/professionSC/{pid}")
    public String professionSC(@PathVariable(name="pid") Integer pid,
                               Model model) {
        Profession profession = professionMapper.selectByPid(pid);
        List<School> schoolList = schoolMapper.findByPid(pid);
        model.addAttribute("professions",profession);
        model.addAttribute("schools", schoolList);
        return "professionSC";
    }

    @GetMapping("/professionSearch")
    public String professionSearch(HttpServletRequest request,
                               Model model,
                               @RequestParam(name = "search",required = false) String search){
        SearchDTO searchs = professionService.list(search);
        model.addAttribute("searchss",searchs);
        return "professionSearch";
    }

    @RequestMapping("/import")
    public String excelImport(@RequestParam(value="filename") MultipartFile file,
                              HttpSession session,
                              HashMap<String, Object> map,
                              Model model){
        int result = 0;
        try{
            result =professionService.addUser(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result>0){
            model.addAttribute("import","excle文件数据导入成功！");
            map.put("path","profession");
        }else {
            model.addAttribute("import","excle文件数据导入失败！");
        }
        return "import";
    }

}
