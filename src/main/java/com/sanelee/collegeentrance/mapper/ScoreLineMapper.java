package com.sanelee.collegeentrance.mapper;

import com.sanelee.collegeentrance.model.ScoreLine;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ScoreLineMapper {
    @Select("select * from scoreline")
    List<ScoreLine> list();
}
