package com.sanelee.collegeentrance.mapper;


import com.sanelee.collegeentrance.model.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Select("select * from question")
    List<Question> list();
}
