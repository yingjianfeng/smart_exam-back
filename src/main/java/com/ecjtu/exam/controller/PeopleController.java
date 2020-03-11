package com.ecjtu.exam.controller;

import com.ecjtu.exam.pojo.Grade;
import com.ecjtu.exam.pojo.People;
import com.ecjtu.exam.pojo.School;
import com.ecjtu.exam.service.IGradeService;
import com.ecjtu.exam.service.IPeopleService;
import com.ecjtu.exam.service.ISchoolService;
import com.ecjtu.exam.util.*;
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
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/queryall")
    public List<People> queryall() {
        return iStudnetService.qryAll();
    }

//    @PostMapping(value = "/login")
    @PostMapping("/login")
    public ResultUtil login(@RequestBody People people) {
        System.out.println(people);
        Map<String, Object> map = new HashMap();
        try {
            People res = iStudnetService.login(people);
            map.put("id", res.getId());
            map.put("name", res.getName());
            map.put("account", res.getAccount());
            map.put("password", res.getPassword());
            map.put("role_id", res.getRole_id());
            String token = jwtUtils.createJwt(map);
            return new ResultUtil(ResultCodeUtil.SUCCESS,token);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil(ResultCodeUtil.USENAMEORPASSWORDERROR,e.getMessage());
        }
    }

    @GetMapping("qryAllGrade")
    public Map qryAllGrade() {
        List<Grade> res = iGradeService.qryAll();
        Map<String, Object> map = new HashMap();
        map.put("grade", res);
        return map;
    }

    @GetMapping("qryAllSchool")
    public Map qryAllSchool() {
        List<School> res = iSchoolService.qryAll();
        Map<String, Object> map = new HashMap();
        map.put("school", res);
        return map;
    }

    @PostMapping("/register")
    public Map register(@RequestBody People people) {
//        System.out.println(people);
        people.setName(CommonUtil.getRandomStr());  //赋予一个随机名称
        System.out.println(people);
        Map<String, Object> map = new HashMap();
        try {
            iStudnetService.register(people);
            map.put("status", ConstUtil.STATE_RETURN_SUCCESS);
        } catch (Exception e) {
            map.put("status", ConstUtil.STATE_RETURN_FAIL);
            map.put("message", e.getMessage());
        }
        return map;
    }

}
