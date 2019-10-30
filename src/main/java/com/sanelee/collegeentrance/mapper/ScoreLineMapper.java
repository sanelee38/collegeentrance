package com.sanelee.collegeentrance.mapper;

import com.sanelee.collegeentrance.model.ScoreLine;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ScoreLineMapper {
    @Select("select * from scoreline")
    List<ScoreLine> list();
    @Select("select * from scoreline where sort = 1")
    List<ScoreLine> listL();
    @Select("select * from scoreline where sort = 2")
    List<ScoreLine> listW();
}
