package com.sanelee.collegeentrance.mapper;

import com.sanelee.collegeentrance.dto.ProfessionQueryDTO;
import com.sanelee.collegeentrance.dto.T_School_ProfessionQueryDTO;
import com.sanelee.collegeentrance.model.Profession;
import com.sanelee.collegeentrance.model.T_School_Profession;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface T_School_ProfessionMapper {

    @Select("SELECT * FROM t_school_profession INNER JOIN profession ON t_school_profession.pid = profession.pid WHERE scid = #{scid};")
    List<T_School_Profession> list(@Param("scid") Integer scid);

    @Select("select * from t_school_profession where minscore regexp #{search}")
    List<T_School_Profession> selectBySearch(T_School_ProfessionQueryDTO t_school_professionQueryDTO);

    @Select("select count(*) from t_school_profession where pid=#{pid} and scid=#{scid} and sort=#{sort}")
    int selectSchoolProfession(Integer pid, Integer scid,Integer sort);

    @Insert("insert into t_school_profession(pid,scid,maxscore,avgscore,minscore,minrank,sort) values(#{pid},#{scid},#{maxscore},#{avgscore},#{minscore},#{minrank},#{sort})")
    int addSchoolProfession(T_School_Profession t_school_professionInfo);

    @Update("update t_school_profession set maxscore=#{maxscore},avgscore=#{avgscore},minscore=#{minscore},minrank=#{minrank} where pid=#{pid} and scid=#{scid} and sort=#{sort}")
    int updateSchoolProfession(T_School_Profession t_school_professionInfo);
}
