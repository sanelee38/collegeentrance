package com.sanelee.collegeentrance.mapper;

import com.sanelee.collegeentrance.model.Profession;
import com.sanelee.collegeentrance.model.School;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProfessionMapper {
    @Select("select * from profession")
    List<Profession> list();

    @Select("SELECT * FROM t_school_profession INNER JOIN profession ON t_school_profession.pid = profession.pid WHERE scid = #{scid};")
    List<Profession> findProByScid(@Param("scid")int scid);
}
