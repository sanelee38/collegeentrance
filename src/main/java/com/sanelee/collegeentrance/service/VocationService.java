package com.sanelee.collegeentrance.service;


import com.sanelee.collegeentrance.mapper.VocationMapper;
import com.sanelee.collegeentrance.model.Vocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VocationService {
    @Autowired
    private VocationMapper vocationMapper;
    public Vocation getByType(String type){
        Vocation vocations=vocationMapper.getvocation(type);
        return vocations;
    }
}
