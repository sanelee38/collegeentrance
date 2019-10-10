package com.sanelee.collegeentrance.mapper;

import com.sanelee.collegeentrance.dto.SchoolQueryDTO;
import com.sanelee.collegeentrance.model.School;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SchoolMapper {

//    @Select("select * from school where area_id=(select aid from area where name ='陕西')")
    @Select("select * from school")
    List<School> list();

    @Select("select * from school where areaid = 1")
    List<School> listBJ();

    @Select("select * from school where areaid = 2")
    List<School> listHB();

    @Select("select * from school where areaid = 3")
    List<School> listCQ();

    @Select("select * from school where areaid = 4")
    List<School> listHN();

    @Select("select * from school where areaid = 5")
    List<School> listYN();

    @Select("select * from school where areaid = 6")
    List<School> listSX();

    @Select("select * from school where areaid = 7")
    List<School> listHLJ();


    @Select("Select * from school where scid = #{scid}")
    School findByScid(@Param("scid") int scid);

    @Select("SELECT school.`scid`, school.`name` FROM school INNER JOIN t_school_profession ON t_school_profession.`scid`=school.`scid` INNER JOIN profession ON profession.`pid`=#{pid} AND t_school_profession.`pid`=profession.`pid`;")
    List<School> findByPid(@Param("pid") int pid);

    @Select("select * from school where school.`name` regexp #{search}")
    List<School> selectBySearch(SchoolQueryDTO schoolQueryDTO);
}
