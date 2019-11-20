package com.sanelee.collegeentrance.mapper;


import com.sanelee.collegeentrance.model.Vocation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface VocationMapper {

    @Select("select * from vocation where type=#{type}")
    Vocation getvocation(@Param("type") String type);
}
