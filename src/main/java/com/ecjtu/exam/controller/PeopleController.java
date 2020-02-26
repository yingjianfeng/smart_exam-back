package com.ecjtu.exam.controller;

import com.ecjtu.exam.pojo.Grade;
import com.ecjtu.exam.pojo.People;
import com.ecjtu.exam.pojo.School;
import com.ecjtu.exam.service.IGradeService;
import com.ecjtu.exam.service.IPeopleService;
import com.ecjtu.exam.service.ISchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PeopleController {
    @Autowired
    IPeopleService iStudnetService;
    @Autowired
    IGradeService iGradeService;
    @Autowired
    ISchoolService iSchoolService;

    @GetMapping("/queryall")
    public List<People> queryall(){
        return iStudnetService.qryAll();
    }

    @PostMapping(value ="/login", produces = "application/json;charset=UTF-8")
    public Map login(@RequestBody People people){
        System.out.println(people);
        Map<String,Object> map = new HashMap();
        try {
            People res = iStudnetService.login(people);
            map.put("status","1");
            map.put("type",res.getType());
            map.put("message",res);
        }catch (Exception e){
            map.put("status","0");
            map.put("message",e.getMessage());
        }
        return map;
    }

    @GetMapping("qryAllGrade")
    public Map qryAllGrade(){
        List<Grade> res = iGradeService.qryAll();
        Map<String,Object> map = new HashMap();
        map.put("grade",res);
        return map;
    }
    @GetMapping("qryAllSchool")
    public Map qryAllSchool(){
        List<School> res = iSchoolService.qryAll();
        Map<String,Object> map = new HashMap();
        map.put("school",res);
        return map;
    }



}
