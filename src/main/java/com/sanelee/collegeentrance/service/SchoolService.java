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

    public int addSchoolScore(MultipartFile file) {
        int result = 0;
        List<T_School_Profession> t_school_professionList = new ArrayList<>();


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

    public int addSchool(MultipartFile file) {
        int result = 0;
        List<School> schoolList = new ArrayList<>();

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
        for (int line = 1; line <= xssfSheet.getLastRowNum(); line++) {

            School school = new School();
            String name = null;
            String areaname = null;
            int areaid = 0;
            String batch = null;
            String description = null;
            String acronym = null;
            String region = null;
            int reid = 0;
            String usedname = null;
            String type = null;
            int foundingYear = 0;
            String department = null;
            String iscombine = null;
            int is985 = 0;
            int is211 = 0;
            String isDoubleFirstClass = null;
            int firstClassNum = 0;
            int facultyNum = 0;
            int academicianNum = 0;
            int changjiangScholarNum = 0;
            int teachersNum = 0;
            int undergraProNum = 0;
            int postgraProNum = 0;
            int doctorProNum = 0;
            int mainLabNum = 0;
            int undergraEnrollNum = 0;
            int postgraEnrollNum = 0;
            String schoolWeb = null;





            XSSFRow xssfRow = xssfSheet.getRow(line);
            if (null == xssfRow) {
                continue;
            }


            XSSFCell nameCell = null;
            nameCell = xssfRow.getCell(1);
            if (nameCell != null){
                name = nameCell.getStringCellValue();
            }else if (nameCell == null){
                name = null;
            }
            XSSFCell areanameCell = null;
            areanameCell = xssfRow.getCell(2);
            if (areanameCell != null){
                areaname = areanameCell.getStringCellValue();
            }else if (areanameCell == null){
                areaname = null;
            }

            XSSFCell areaidCell = null;
            areaidCell = xssfRow.getCell(3);
            if (areaidCell != null) {
                areaid = (int) areaidCell.getNumericCellValue();
            }else if (areaidCell == null){
                areaid = 0;
            }

            XSSFCell batchCell = null;
            batchCell = xssfRow.getCell(4);
            if (batchCell != null){
                batch = batchCell.getStringCellValue();
            }else if (batchCell == null){
                batch = null;
            }

            XSSFCell usednameCell = null;
            usednameCell = xssfRow.getCell(5);
            if (usednameCell != null){
                usedname = usednameCell.getStringCellValue();
            }else if (usednameCell == null){
                usedname = null;
            }

            XSSFCell acronymCell = null;
            acronymCell = xssfRow.getCell(6);
            if (acronymCell != null){
                acronym = acronymCell.getStringCellValue();
            }else if (acronymCell == null){
                acronym = null;
            }

            XSSFCell descriptionCell = null;
            descriptionCell = xssfRow.getCell(7);
            if (descriptionCell != null){
                description = descriptionCell.getStringCellValue();
            }else if (descriptionCell == null){
                description = null;
            }

            XSSFCell typeCell = null;
            typeCell = xssfRow.getCell(8);
            if (typeCell != null){
                type = typeCell.getStringCellValue();
            }else if (typeCell == null){
                type = null;
            }

            XSSFCell foundingYearCell = null;
            foundingYearCell = xssfRow.getCell(9);
            if (foundingYearCell != null) {
                foundingYear = (int) foundingYearCell.getNumericCellValue();
            }else if (foundingYearCell == null){
                foundingYear= 0;
            }

            XSSFCell departmentCell = null;
            departmentCell = xssfRow.getCell(10);
            if (departmentCell != null){
                department = departmentCell.getStringCellValue();
            }else if (departmentCell == null){
                department = null;
            }

            XSSFCell iscombineCell = null;
            iscombineCell = xssfRow.getCell(11);
            if (iscombineCell != null){
                iscombine = iscombineCell.getStringCellValue();
            }else if (iscombineCell == null){
                iscombine = null;
            }


            XSSFCell is985Cell = null;
            is985Cell = xssfRow.getCell(12);
            if (is985Cell != null) {
                is985 = (int) is985Cell.getNumericCellValue();
            }else if (is985Cell == null){
                is985 = 0;
            }

            XSSFCell is211Cell = null;
            is211Cell = xssfRow.getCell(13);
            if (is211Cell != null) {
                is211 = (int) is211Cell.getNumericCellValue();
            }else if (is211Cell == null){
                is211 = 0;
            }

            XSSFCell isDoubleFirstClassCell = null;
            isDoubleFirstClassCell = xssfRow.getCell(14);
            if (isDoubleFirstClassCell != null){
                isDoubleFirstClass = isDoubleFirstClassCell.getStringCellValue();
            }else if (isDoubleFirstClassCell == null){
                isDoubleFirstClass = null;
            }

            XSSFCell firstClassNumCell = null;
            firstClassNumCell = xssfRow.getCell(15);
            if (firstClassNumCell != null){
                firstClassNum = (int) firstClassNumCell.getNumericCellValue();
            }else if (firstClassNumCell == null){
                firstClassNum = 0;
            }

            XSSFCell facultyNumCell = null;
            facultyNumCell = xssfRow.getCell(16);
            if (facultyNumCell != null){
                facultyNum = (int) facultyNumCell.getNumericCellValue();
            }else if (facultyNumCell == null){
                facultyNum = 0;
            }

            XSSFCell academicianNumCell = null;
            academicianNumCell = xssfRow.getCell(17);
            if (academicianNumCell != null){
                academicianNum = (int) academicianNumCell.getNumericCellValue();
            }else if (academicianNumCell == null){
                academicianNum = 0;
            }


            XSSFCell changjiangScholarNumCell = null;
            changjiangScholarNumCell = xssfRow.getCell(18);
            if (changjiangScholarNumCell != null){
                changjiangScholarNum = (int) changjiangScholarNumCell.getNumericCellValue();
            }else if (changjiangScholarNumCell == null){
                changjiangScholarNum = 0;
            }


            XSSFCell teachersNumCell = null;
            teachersNumCell = xssfRow.getCell(19);
            if (teachersNumCell != null){
                teachersNum = (int) teachersNumCell.getNumericCellValue();
            }else if (teachersNumCell == null){
                teachersNum = 0;
            }

            XSSFCell undergraProNumCell = null;
            undergraProNumCell = xssfRow.getCell(20);
            if (undergraProNumCell != null){
                undergraProNum = (int) undergraProNumCell.getNumericCellValue();
            }else if (undergraProNumCell == null){
                undergraProNum = 0;
            }

            XSSFCell postgraProNumCell = null;
            postgraProNumCell = xssfRow.getCell(21);
            if (postgraProNumCell != null){
                postgraProNum = (int) postgraProNumCell.getNumericCellValue();
            }else if (postgraProNumCell == null){
                postgraProNum = 0;
            }

            XSSFCell doctorProNumCell = null;
            doctorProNumCell = xssfRow.getCell(22);
            if (doctorProNumCell != null){
                doctorProNum = (int) doctorProNumCell.getNumericCellValue();
            }else if (doctorProNumCell == null){
                doctorProNum = 0;
            }

            XSSFCell mainLabNumCell = null;
            mainLabNumCell = xssfRow.getCell(23);
            if (mainLabNumCell != null){
                mainLabNum = (int) mainLabNumCell.getNumericCellValue();
            }else if (mainLabNumCell == null){
                mainLabNum = 0;
            }

            XSSFCell undergraEnrollNumCell = null;
            undergraEnrollNumCell = xssfRow.getCell(24);
            if (undergraEnrollNumCell != null){
                undergraEnrollNum = (int) undergraEnrollNumCell.getNumericCellValue();
            }else if (undergraEnrollNumCell == null){
                undergraEnrollNum = 0;
            }

            XSSFCell postgraEnrollNumCell = null;
            postgraEnrollNumCell = xssfRow.getCell(25);
            if (postgraEnrollNumCell != null){
                postgraEnrollNum = (int) postgraEnrollNumCell.getNumericCellValue();
            }else if (postgraEnrollNumCell == null){
                postgraEnrollNum = 0;
            }

            XSSFCell schoolWebCell = null;
            schoolWebCell = xssfRow.getCell(26);
            if (schoolWebCell != null){
                schoolWeb = schoolWebCell.getStringCellValue();
            }else if (schoolWebCell == null){
                schoolWeb = null;
            }

            XSSFCell regionCell = null;
            regionCell = xssfRow.getCell(27);
            if (regionCell != null){
                region = regionCell.getStringCellValue();
            }else if (regionCell == null){
                region = null;
            }

            XSSFCell reidCell = null;
            reidCell = xssfRow.getCell(28);
            if (reidCell != null){
                reid = (int) reidCell.getNumericCellValue();
            }else if (reidCell == null){
                reid = 0;
            }




            school.setName(name);
            school.setAreaname(areaname);
            school.setAreaid(areaid);
            school.setBatch(batch);
            school.setUsedname(usedname);
            school.setAcronym(acronym);
            school.setDescription(description);
            school.setType(type);
            school.setFoundingYear(foundingYear);
            school.setDepartment(department);
            school.setIscombine(iscombine);
            school.setIs985(is985);
            school.setIs211(is211);
            school.setIsDoubleFirstClass(isDoubleFirstClass);
            school.setFirstClassNum(firstClassNum);
            school.setFacultyNum(facultyNum);
            school.setAcademicianNum(academicianNum);
            school.setChangjiangScholarNum(changjiangScholarNum);
            school.setTeachersNum(teachersNum);
            school.setUndergraProNum(undergraProNum);
            school.setPostgraProNum(postgraProNum);
            school.setDoctorProNum(doctorProNum);
            school.setMainLabNum(mainLabNum);
            school.setUndergraEnrollNum(undergraEnrollNum);
            school.setPostgraEnrollNum(postgraEnrollNum);
            school.setSchoolWeb(schoolWeb);
            school.setReid(reid);
            school.setRegion(region);

            schoolList.add(school);

        }

        for (School schoolInfo : schoolList) {

            String name = schoolInfo.getName();

            int count = schoolMapper.selectSchool(name);
            if (0 == count) {
                result = schoolMapper.addSchool(schoolInfo);
            } else {
                result = schoolMapper.updateSchool(schoolInfo);
            }
        }

        return result;
    }
}
