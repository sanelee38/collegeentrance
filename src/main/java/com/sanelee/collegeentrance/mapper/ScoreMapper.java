package com.sanelee.collegeentrance.mapper;

import com.sanelee.collegeentrance.model.Score;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface ScoreMapper {
    @Select("select * from score where aid=6")
    List<Score> list();

    @Select("select * from score where scid=#{scid} and sid = 1 and aid = 6")
    List<Score> listL(@Param("scid") Integer scid);

    @Select("select * from score where scid=#{scid} and sid = 2 and aid = 6")
    List<Score> listW(@Param("scid") Integer scid);

    @Select("select * from score where scid=#{scid}")
    @Results({
            @Result(property = "school",column = "scid",
                    one=@One(select = "com.sanelee.collegeentrance.mapper.SchoolMapper.findByScid")),
    })
    List<Score> findByScid(int scid);

    @Select("select * from score where scid = #{scid}")
    List<Score> getByScid(@Param("scid") Integer scid);

}
