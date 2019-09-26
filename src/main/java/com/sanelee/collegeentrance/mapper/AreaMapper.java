package com.sanelee.collegeentrance.mapper;

import com.sanelee.collegeentrance.model.Area;
import com.sanelee.collegeentrance.model.School;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AreaMapper {
    @Select("select * from area")
    List<Area> list();

    @Select("Select * from area where aid = #{aid}")
    School findByAid(@Param("aid") int aid);

}
