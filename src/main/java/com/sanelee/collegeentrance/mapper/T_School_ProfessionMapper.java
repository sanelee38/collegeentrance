package com.sanelee.collegeentrance.mapper;

import com.sanelee.collegeentrance.dto.ProfessionQueryDTO;
import com.sanelee.collegeentrance.dto.T_School_ProfessionQueryDTO;
import com.sanelee.collegeentrance.model.Profession;
import com.sanelee.collegeentrance.model.T_School_Profession;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface T_School_ProfessionMapper {

    @Select("SELECT * FROM t_school_profession INNER JOIN profession ON t_school_profession.pid = profession.pid WHERE scid = #{scid};")
    List<T_School_Profession> list(@Param("scid") Integer scid);

    @Select("select * from t_school_profession where minscore regexp #{search}")
    List<T_School_Profession> selectBySearch(T_School_ProfessionQueryDTO t_school_professionQueryDTO);
}
