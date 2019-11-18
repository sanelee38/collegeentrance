package com.sanelee.collegeentrance.service;

import com.sanelee.collegeentrance.dto.GaokaoQueryDTO;
import com.sanelee.collegeentrance.mapper.GaokaoMapper;
import com.sanelee.collegeentrance.model.Gaokao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GaokaoService {
    @Autowired
    private GaokaoMapper gaokaoMapper;

    public List<Gaokao> gaokaoQuery(Integer score, String province, String object, String direction) {
        GaokaoQueryDTO gaokaoQueryDTO = new GaokaoQueryDTO();
        gaokaoQueryDTO.setDirection(direction);
        gaokaoQueryDTO.setProvince(province);
        gaokaoQueryDTO.setObject(object);
        gaokaoQueryDTO.setScore(score);
        List<Gaokao> gaokaoList = gaokaoMapper.searchByScore_Province_Object_Direction(gaokaoQueryDTO);
        return gaokaoList;
    }
}
