package com.sanelee.collegeentrance.mapper;

import com.sanelee.collegeentrance.dto.ProfessionQueryDTO;
import com.sanelee.collegeentrance.dto.SchoolQueryDTO;
import com.sanelee.collegeentrance.model.Profession;
import com.sanelee.collegeentrance.model.School;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProfessionMapper {
    @Select("select * from profession ORDER BY pid")
    List<Profession> list();

    @Select("select * from profession where pid = #{pid}")
    Profession selectByPid(@Param("pid")int pid);

    @Select("SELECT * FROM t_school_profession INNER JOIN profession ON t_school_profession.pid = profession.pid WHERE scid = #{scid};")
    List<Profession> findProByScid(@Param("scid")int scid);

    @Select("select * from profession where profession.`proname` regexp #{search}")
    List<Profession> selectBySearch(ProfessionQueryDTO professionQueryDTO);

    @Select("select count(*) from profession where proname=#{proname}")
    int selectProfession(String proname);

    @Insert("insert into profession (proname,object,pfdescription) values(#{proname},#{object},#{pfdescription})")
    int addProfession(Profession professionInfo);

    @Update("update profession set object = #{object},pfdescription = #{pfdescription} where proname = #{proname}")
    int updateProfession(Profession professionInfo);

    @Select("SELECT DISTINCT object,obid FROM profession;")
    List<Profession> objectObid();

    @Select("select * from profession where obid=#{obid}")
    List<Profession> selectByObid(@Param("obid") int obid);
}
