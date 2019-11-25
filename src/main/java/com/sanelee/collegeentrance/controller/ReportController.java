package com.sanelee.collegeentrance.controller;


import com.sanelee.collegeentrance.Util.ExportWordUtils;
import com.sanelee.collegeentrance.mapper.AreaMapper;
import com.sanelee.collegeentrance.mapper.UserMapper;
import com.sanelee.collegeentrance.model.Gaokao;
import com.sanelee.collegeentrance.model.User;
import com.sanelee.collegeentrance.service.GaokaoService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ReportController {
    @Autowired
    private GaokaoService gaokaoService;
    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private UserMapper userMapper;
    @RequestMapping("/reportZhiyuan")
    public String reportZhiyuan(Model model){
        return "reportZhiyuan";
    }
    @RequestMapping(value = "/exportreportZhiyuan",method=RequestMethod.POST)
    public void reportZhiyuan(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(name = "username",required = false) String username,
                                @RequestParam(name = "province1",required = false) String province1,
                                @RequestParam(name = "province2",required = false) String province2,
                                @RequestParam(name = "province3",required = false) String province3,
                                @RequestParam(name = "direction1",required = false) String direction1,
                                @RequestParam(name = "direction2",required = false) String direction2,
                                @RequestParam(name = "direction3",required = false) String direction3,
                                @RequestParam(name = "direction4",required = false) String direction4,
                                @RequestParam(name = "direction5",required = false) String direction5,
                                @RequestParam(name = "direction6",required = false) String direction6){
        User user=userMapper.selectUserInfoByUsername(username);
        String name=user.getUsername();
        String gender = user.getUserGender();
        String phone = user.getUserPhone();
        String wechat = user.getUserWechat();
        String sort = user.getUserSort();
        String area = user.getUserArea();
        int score = user.getUserScore();
        int rank = user.getUserRank();

        Map<String,Object> params = new HashMap<>();
        params.put("userName",name);
        params.put("userGender",gender);
        params.put("userNumber",phone);
        params.put("userWechat",wechat);
        params.put("userSort",sort);
        params.put("userArea",area);
        params.put("userScore",score);
        params.put("userRank",rank);

        InputStream is = this.getClass().getResourceAsStream("/word/template.docx");
        ExportWordUtils.exportWord(is,"D:/test","高考志愿报告.docx",params,request,response);
    }
    @RequestMapping(value = "GaoKaoQueryReportExcelDownloads",method = RequestMethod.GET)
    public void downloadAllClassmate(HttpServletResponse response,
                                     Model model,
                                     @RequestParam(name="score",required = false) Integer score,
                                     @RequestParam(name="province",required = false) String province,
                                     @RequestParam(name="object",required = false) String object,
                                     @RequestParam(name="direction",required = false) String direction) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("高考查询报告表");

        List<Gaokao> gaokaoList = gaokaoService.gaokaoQuery(score, province, object, direction);
        model.addAttribute("gaokao", gaokaoList);



        String fileName = "高考查询报告表" + ".xls";

        int rowNum = 1;

        String[] headers = {"专业名","学校名"};

        HSSFRow row = sheet.createRow(0);

        for (int i=0; i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        for (Gaokao gaokao:gaokaoList){
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(gaokao.getSpname());
            row1.createCell(1).setCellValue(gaokao.getName());
            rowNum++;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName,"utf-8"));
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }
}
