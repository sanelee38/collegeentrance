package com.sanelee.collegeentrance.mapper;


import com.sanelee.collegeentrance.dto.GaokaoQueryDTO;
import com.sanelee.collegeentrance.model.Gaokao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GaokaoMapper {
    @Select("select * from gaokao where min<#{score}+5 and min>#{score}-5 and local_province_name=#{province} and local_type_name=#{object}")
    List<Gaokao> searchByScore_Province_Object_Direction(GaokaoQueryDTO gaokaoQueryDTO);

}
