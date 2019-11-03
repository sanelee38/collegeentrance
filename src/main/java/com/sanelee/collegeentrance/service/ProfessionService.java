package com.sanelee.collegeentrance.service;

import com.sanelee.collegeentrance.dto.*;
import com.sanelee.collegeentrance.exception.MyException;
import com.sanelee.collegeentrance.mapper.ProfessionMapper;
import com.sanelee.collegeentrance.mapper.SchoolMapper;
import com.sanelee.collegeentrance.mapper.T_School_ProfessionMapper;
import com.sanelee.collegeentrance.model.Profession;
import com.sanelee.collegeentrance.model.School;
import com.sanelee.collegeentrance.model.T_School_Profession;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProfessionService {
    @Autowired
    private T_School_ProfessionMapper t_school_professionMapper;

    @Autowired
    private SchoolMapper schoolMapper;
    @Autowired
    private ProfessionMapper professionMapper;

    public List<T_School_ProfessionDTO> findScoreByScid(Integer scid) {
        List<T_School_Profession> t_school_professions = t_school_professionMapper.list(scid);
        List<T_School_ProfessionDTO> t_school_professionDTOList = new ArrayList<>();
        for (T_School_Profession t_school_profession : t_school_professions) {
            School school = schoolMapper.findByScid(t_school_profession.getScid());
            T_School_ProfessionDTO t_school_professionDTO = new T_School_ProfessionDTO();
            BeanUtils.copyProperties(t_school_profession, t_school_professionDTO);
            t_school_professionDTO.setSchool(school);
            t_school_professionDTOList.add(t_school_professionDTO);
        }
        return t_school_professionDTOList;
    }

    public SearchDTO list(String search) {
        if (StringUtils.isNotBlank(search)) {
            String[] tags = StringUtils.split(search, " ");
            search = Arrays.stream(tags).collect(Collectors.joining("|"));
        }
        SearchDTO searchDTO = new SearchDTO();
        ProfessionQueryDTO professionQueryDTO = new ProfessionQueryDTO();
        professionQueryDTO.setSearch(search);
        List<Profession> professions = professionMapper.selectBySearch(professionQueryDTO);
        List<ProfessionDTO> professionDTOList = new ArrayList<>();

        for (Profession profession : professions) {
            ProfessionDTO professionDTO = new ProfessionDTO();
            BeanUtils.copyProperties(profession, professionDTO);
            professionDTOList.add(professionDTO);
        }
        searchDTO.setProfessions(professionDTOList);
        return searchDTO;

    }

    public int addUser(MultipartFile file) {



        int result = 0;
        List<Profession> professionList = new ArrayList<>();

        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);

        InputStream ins = null;
        try {
            ins = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        XSSFWorkbook xssfWorkbook = null;
        try {
            xssfWorkbook = new XSSFWorkbook(ins);
        } catch (IOException e) {
            e.printStackTrace();
        }

        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);

        for (int line = 2; line <= xssfSheet.getLastRowNum(); line++) {

            Profession profession = new Profession();
            String proname = null;
            String object = null;
            String pfdescription = null;

            XSSFRow xssfRow = xssfSheet.getRow(line);
            if (null == xssfRow) {
                continue;
            }

//            String proname = xssfRow.getCell().getStringCellValue();
//            String object = xssfRow.getCell(2)

            XSSFCell pronameCell = null;
            pronameCell = xssfRow.getCell(0);
            if (pronameCell != null) {
                proname = pronameCell.getStringCellValue();
            }else if (pronameCell == null){
                proname = "暂无";
            }
            XSSFCell objectCell = null;
            objectCell = xssfRow.getCell(1);
            if (objectCell != null){
                object = objectCell.getStringCellValue();
            }else if (objectCell == null){
                object = "暂无";
            }

            XSSFCell pfdescriptionCell = null;
            pfdescriptionCell = xssfRow.getCell(2);
            if (pfdescriptionCell != null) {
                pfdescription = pfdescriptionCell.getStringCellValue();
            }else if (pfdescriptionCell == null){
                pfdescription = "暂无";
            }


                profession.setProname(proname);
                profession.setObject(object);
                profession.setPfdescription(pfdescription);
                professionList.add(profession);

        }

        for (Profession professionInfo : professionList) {
            String proname = professionInfo.getProname();
            int count = professionMapper.selectProfession(proname);
            if (0 == count) {
                result = professionMapper.addProfession(professionInfo);
            } else {
                result = professionMapper.updateProfession(professionInfo);
            }
        }

        return result;
    }
}
