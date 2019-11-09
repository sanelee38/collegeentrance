package com.sanelee.collegeentrance.controller;


import com.sanelee.collegeentrance.dto.SchoolDTO;
import com.sanelee.collegeentrance.dto.SearchDTO;
import com.sanelee.collegeentrance.mapper.AreaMapper;
import com.sanelee.collegeentrance.mapper.ProfessionMapper;
import com.sanelee.collegeentrance.mapper.SchoolMapper;
import com.sanelee.collegeentrance.model.Area;
import com.sanelee.collegeentrance.model.Profession;
import com.sanelee.collegeentrance.model.School;
import com.sanelee.collegeentrance.service.SchoolService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
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
        List<School> regionList = schoolMapper.regionReid();
        model.addAttribute("areas",areaList);
        model.addAttribute("schools",schoolList);
        model.addAttribute("schoolsBj",schoolListBj);
        model.addAttribute("schoolsCq",schoolListCq);
        model.addAttribute("schoolsSx",schoolListSx);
        model.addAttribute("schoolsHn",schoolListHn);
        model.addAttribute("schoolsYn",schoolListYn);
        model.addAttribute("schoolsHb",schoolListHb);
        model.addAttribute("schoolsHlj",schoolListHlj);
        model.addAttribute("regions",regionList);
        return "school";
    }

    @RequestMapping("/schoolList/{reid}")
    public String schoolList(@PathVariable(name="reid") Integer reid,
                                  Model model){
        List<School> schoolList = schoolMapper.selectByReid(reid);
        List<School> areaList = schoolMapper.selectAreaByReid(reid);
        model.addAttribute("schools",schoolList);
        model.addAttribute("areas",areaList);
        return "schoolList";
    }
    @GetMapping("/schoolSearchList")
    public String schoolSearchList(Model model,
                                   @RequestParam(name = "search",required = false) String search){
        SearchDTO searchList = schoolService.list(search);
        model.addAttribute("searchs",searchList);
        return "schoolSearchList";
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


    @RequestMapping(value = "SchoolExcelDownloads",method = RequestMethod.GET)
    public void downloadAllClassmate(HttpServletResponse response,
                                     Model model,
                                     @RequestParam(name = "select",required = false) String select,
                                     @RequestParam(name = "proSearch",required = false) String proSearch) throws IOException{
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("学校表");

        List<SchoolDTO> schoolDTOList = schoolService.schoolinfor(proSearch,select);
        model.addAttribute("printer",schoolDTOList);

        System.out.println(proSearch);

        String fileName = select +"地区包含"+proSearch+"专业的学校汇总表" + ".xls";

        int rowNum = 1;

        String[] headers = {"学校id","学校名","学校所在地","专业","最高分","平均分","最低分","最低位次"};

        HSSFRow row = sheet.createRow(0);

        for (int i=0; i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        for (SchoolDTO schoolDTO:schoolDTOList){
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(schoolDTO.getScid());
            row1.createCell(1).setCellValue(schoolDTO.getName());
            row1.createCell(2).setCellValue(schoolDTO.getAreaname());
            row1.createCell(3).setCellValue(schoolDTO.getProname());
            row1.createCell(4).setCellValue(schoolDTO.getMaxscore());
            row1.createCell(5).setCellValue(schoolDTO.getAvgscore());
            row1.createCell(6).setCellValue(schoolDTO.getMinscore());
            row1.createCell(7).setCellValue(schoolDTO.getMinrank());
            rowNum++;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName,"utf-8"));
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }
    @RequestMapping("/importSchoolScore")
    public String excelImportScore(@RequestParam(value="filename") MultipartFile file,
                              HashMap<String, Object> map,
                              Model model){
        int result = 0;
        try{
            result =schoolService.addSchoolScore(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result>0){
            model.addAttribute("import","excle文件数据导入成功！");
            map.put("path","school");
        }else {
            model.addAttribute("import","excle文件数据导入失败！");
            map.put("path","school");
        }
        return "import";
    }

    @RequestMapping("/importSchool")
    public String excelImportSchool(@RequestParam(value="filename") MultipartFile file,
                                    HashMap<String, Object> map,
                                    Model model){
        int result = 0;
        try{
            result =schoolService.addSchool(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result>0){
            model.addAttribute("import","excle文件数据导入成功！");
            map.put("path","school");
        }else {
            model.addAttribute("import","excle文件数据导入失败！");
            map.put("path","school");
        }
        return "import";
    }
}
