package com.sanelee.collegeentrance.mapper;

import com.sanelee.collegeentrance.model.Score;
import com.sanelee.collegeentrance.model.Score2017;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface Score2017Mapper {
    @Select("select * from 2017score")
    List<Score2017> list();

    @Select("select * from 2017score where scid=#{scid} and sid = 1")
    List<Score2017> listL(@Param("scid") Integer scid);

    @Select("select * from 2017score where scid=#{scid} and sid = 2")
    List<Score2017> listW(@Param("scid") Integer scid);

    @Select("select * from 2017score where scid=#{scid}")
    @Results({
            @Result(property = "school",column = "scid",
                    one=@One(select = "com.sanelee.collegeentrance.mapper.SchoolMapper.findByScid")),
    })
    List<Score2017> findByScid(int scid);

    @Select("select * from 2017score where scid = #{scid}")
    List<Score2017> getByScid(@Param("scid") Integer scid);
}
