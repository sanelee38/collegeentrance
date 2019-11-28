package com.sanelee.collegeentrance.mapper;


import com.sanelee.collegeentrance.dto.GaokaoQueryDTO;
import com.sanelee.collegeentrance.model.Gaokao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GaokaoMapper {
    @Select("select * from gaokao where min<#{score}+5 and min>#{score}-5 and local_province_name=#{province} and local_type_name=#{object}")
    List<Gaokao> searchByScore_Province_Object_Direction(GaokaoQueryDTO gaokaoQueryDTO);

    @Select("select NAME ,COUNT(*),spname,rank FROM gaokao where min between #{score}-5 and #{score}+10 AND areaname REGEXP '${province1}|${province2}|${province3}' and local_province_name =#{area} GROUP BY NAME HAVING COUNT(*)>=6 ORDER BY rank")
    List<Gaokao> selectChongBySelect(@Param("score") int score,@Param("area") String area,@Param("province1") String province1,@Param("province2") String province2,@Param("province3") String province3);

    @Select("select NAME ,COUNT(*),spname,rank FROM gaokao where min between #{score}-20 and #{score}-6 AND areaname REGEXP '${province1}|${province2}|${province3}' and local_province_name =#{area} GROUP BY NAME HAVING COUNT(*)>=6 ORDER BY rank")
    List<Gaokao> selectWenBySelect(@Param("score") int score,@Param("area") String area,@Param("province1") String province1,@Param("province2") String province2,@Param("province3") String province3);

    @Select("select NAME ,COUNT(*),spname,rank FROM gaokao where min between #{score}-40 and #{score}-21 AND areaname REGEXP '${province1}|${province2}|${province3}' and local_province_name =#{area} GROUP BY NAME HAVING COUNT(*)>=6 ORDER BY rank")
    List<Gaokao> selectBaoBySelect(@Param("score") int score,@Param("area") String area,@Param("province1") String province1,@Param("province2") String province2,@Param("province3") String province3);

    @Select("select * from gaokao where min between #{score}-5 and #{score}+10 and name=#{school} and local_province_name =#{area}")
    List<Gaokao> selectChongSchoolProfession(@Param("score") int score,@Param("school") String chongschool1,@Param("area") String area);
}