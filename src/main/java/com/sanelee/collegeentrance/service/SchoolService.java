package com.sanelee.collegeentrance.service;

import com.sanelee.collegeentrance.dto.SchoolDTO;
import com.sanelee.collegeentrance.dto.SchoolQueryDTO;
import com.sanelee.collegeentrance.dto.SearchDTO;
import com.sanelee.collegeentrance.mapper.SchoolMapper;
import com.sanelee.collegeentrance.mapper.T_School_ProfessionMapper;
import com.sanelee.collegeentrance.model.Profession;
import com.sanelee.collegeentrance.model.School;
import com.sanelee.collegeentrance.model.T_School_Profession;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {
    @Autowired
    private SchoolMapper schoolMapper;
    @Autowired
    private T_School_ProfessionMapper t_school_professionMapper;

    public SearchDTO list(String search){
        if(StringUtils.isNotBlank(search)){
            String[] tags = StringUtils.split(search," ");
            search = Arrays.stream(tags).collect(Collectors.joining("|"));
        }
        SearchDTO searchDTO = new SearchDTO();
        SchoolQueryDTO schoolQueryDTO = new SchoolQueryDTO();
        schoolQueryDTO.setSearch(search);
        List<School> schools = schoolMapper.selectBySearch(schoolQueryDTO);
        List<SchoolDTO> schoolDTOList = new ArrayList<>();
        for (School school : schools){
            SchoolDTO schoolDTO = new SchoolDTO();
            BeanUtils.copyProperties(school,schoolDTO);
            schoolDTOList.add(schoolDTO);
        }
        searchDTO.setSchools(schoolDTOList);
        return searchDTO;

    }
    public SearchDTO selectList(String select){
        if(StringUtils.isNotBlank(select)){
            String[] tags = StringUtils.split(select,",");
            select = Arrays.stream(tags).collect(Collectors.joining("|"));
        }
        SearchDTO searchDTO = new SearchDTO();
        SchoolQueryDTO schoolQueryDTO = new SchoolQueryDTO();
        schoolQueryDTO.setSelect(select);
        List<School> schools = schoolMapper.selectBySelect(schoolQueryDTO);
        List<SchoolDTO> schoolDTOList = new ArrayList<>();
        for (School school : schools){
            SchoolDTO schoolDTO = new SchoolDTO();
            BeanUtils.copyProperties(school,schoolDTO);
            schoolDTOList.add(schoolDTO);
        }
        searchDTO.setSchools(schoolDTOList);
        return searchDTO;
    }

    public SearchDTO proSearchList(String proSearch){
        if (StringUtils.isNotBlank(proSearch)){
            String[] tags = StringUtils.split(proSearch," ");
            proSearch = Arrays.stream(tags).collect(Collectors.joining("|"));
        }
        SearchDTO searchDTO = new SearchDTO();
        SchoolQueryDTO schoolQueryDTO = new SchoolQueryDTO();
        schoolQueryDTO.setProSearch(proSearch);
        List<School> schools = schoolMapper.selectByProSearch(schoolQueryDTO);
        List<SchoolDTO> schoolDTOList = new ArrayList<>();
        for (School school:schools){
            SchoolDTO schoolDTO = new SchoolDTO();
            BeanUtils.copyProperties(school,schoolDTO);
            schoolDTOList.add(schoolDTO);
        }
        searchDTO.setSchools(schoolDTOList);
        return searchDTO;
    }

    public SearchDTO proSearchsList(String proSearch,String select) {
        if(StringUtils.isNotBlank(proSearch)){
            String[] tags = StringUtils.split(proSearch," ");
            proSearch = Arrays.stream(tags).collect(Collectors.joining("|"));
            String[] tags2 = StringUtils.split(select,",");
            select = Arrays.stream(tags2).collect(Collectors.joining("|"));
        }

        SearchDTO searchDTO = new SearchDTO();
        SchoolQueryDTO schoolQueryDTO = new SchoolQueryDTO();
        schoolQueryDTO.setProSearch(proSearch);
        schoolQueryDTO.setSelect(select);
        List<School> schools = schoolMapper.selectByProSelect(schoolQueryDTO);
        List<SchoolDTO> schoolDTOList = new ArrayList<>();
        for (School school : schools){
            SchoolDTO schoolDTO = new SchoolDTO();
            BeanUtils.copyProperties(school,schoolDTO);
            schoolDTOList.add(schoolDTO);
        }
        searchDTO.setSchools(schoolDTOList);
        return searchDTO;
    }

    public List<SchoolDTO> schoolinfor(String proSearch,String select){
        if(StringUtils.isNotBlank(proSearch)){
            String[] tags = StringUtils.split(proSearch," ");
            proSearch = Arrays.stream(tags).collect(Collectors.joining("|"));
            String[] tags2 = StringUtils.split(select,",");
            select = Arrays.stream(tags2).collect(Collectors.joining("|"));
        }
        SchoolQueryDTO schoolQueryDTO = new SchoolQueryDTO();
        schoolQueryDTO.setProSearch(proSearch);
        schoolQueryDTO.setSelect(select);
        List<SchoolDTO> schoolDTOList = schoolMapper.selectByPrinter(schoolQueryDTO);
        return schoolDTOList;
    }

    public int addSchool(MultipartFile file) {
        int result = 0;
        List<T_School_Profession> t_school_professionList = new ArrayList<>();

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

            T_School_Profession t_school_profession = new T_School_Profession();
            int pid = 0;
            int scid = 0;
            int maxscore =0;
            int avgscore = 0;
            int minscore = 0;
            int minrank = 0;
            int sort = 0;

            XSSFRow xssfRow = xssfSheet.getRow(line);
            if (null == xssfRow) {
                continue;
            }


            XSSFCell pidCell = null;
            pidCell = xssfRow.getCell(0);
            if (pidCell != null) {
                pid = (int) pidCell.getNumericCellValue();
            }else if (pidCell == null){
                pid = 0;
            }
            XSSFCell scidCell = null;
            scidCell = xssfRow.getCell(1);
            if (scidCell != null){
                scid = (int) scidCell.getNumericCellValue();
            }else if (scidCell == null){
                scid = 0;
            }

            XSSFCell maxscoreCell = null;
            maxscoreCell = xssfRow.getCell(2);
            if (maxscoreCell != null) {
                maxscore = (int) maxscoreCell.getNumericCellValue();
            }else if (maxscoreCell == null){
                maxscore = 0;
            }

            XSSFCell avgscoreCell = null;
            avgscoreCell = xssfRow.getCell(3);
            if (avgscoreCell != null) {
                avgscore = (int) avgscoreCell.getNumericCellValue();
            }else if (avgscoreCell == null){
                avgscore = 0;
            }

            XSSFCell minscoreCell = null;
            minscoreCell = xssfRow.getCell(4);
            if (minscoreCell != null) {
                minscore = (int) minscoreCell.getNumericCellValue();
            }else if (minscoreCell == null){
                minscore = 0;
            }

            XSSFCell minrankCell = null;
            minrankCell = xssfRow.getCell(5);
            if (minrankCell != null) {
                minrank = (int) minrankCell.getNumericCellValue();
            }else if (minrankCell == null){
                minrank = 0;
            }

            XSSFCell sortCell = null;
            sortCell = xssfRow.getCell(6);
            if (sortCell != null) {
                sort = (int) sortCell.getNumericCellValue();
            }else if (sortCell == null){
                sort = 0;
            }
            System.out.println(pid);
            System.out.println(scid);
            System.out.println(maxscore);
            System.out.println(avgscore);
            System.out.println(minscore);
            System.out.println(minrank);
            System.out.println(sort);

            t_school_profession.setPid(pid);
            t_school_profession.setScid(scid);
            t_school_profession.setMaxscore(maxscore);
            t_school_profession.setAvgscore(avgscore);
            t_school_profession.setMinscore(minscore);
            t_school_profession.setMinrank(minrank);
            t_school_profession.setSort(sort);
            t_school_professionList.add(t_school_profession);

        }

        for (T_School_Profession t_school_professionInfo : t_school_professionList) {
            Integer pid = t_school_professionInfo.getPid();
            Integer scid = t_school_professionInfo.getScid();
            Integer sort = t_school_professionInfo.getSort();
            int count = t_school_professionMapper.selectSchoolProfession(pid,scid,sort);
            if (0 == count) {
                result = t_school_professionMapper.addSchoolProfession(t_school_professionInfo);
            } else {
                result = t_school_professionMapper.updateSchoolProfession(t_school_professionInfo);
            }
        }
        return result;
    }
}
