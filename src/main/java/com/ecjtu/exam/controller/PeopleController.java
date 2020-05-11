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
            map.put("img", res.getImg());
            String token = jwtUtils.createJwt(map);
            return new ResultUtil(ResultCodeUtil.SUCCESS, token);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil(ResultCodeUtil.USENAMEORPASSWORDERROR, e.getMessage());
        }
    }

    @GetMapping("/qryAllGrade")
    public ResultUtil qryAllGrade() {
        List<Grade> res = null;
        try {
            res = iGradeService.qryAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil(ResultCodeUtil.FAIL, null);
        }
        Map<String, Object> map = new HashMap();
        return new ResultUtil(ResultCodeUtil.SUCCESS, res);
    }

    @GetMapping("/qryAllSchool")
    public ResultUtil qryAllSchool() {
        List<School> res = null;
        try {
            res = iSchoolService.qryAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil(ResultCodeUtil.FAIL);
        }
        return new ResultUtil(ResultCodeUtil.SUCCESS, res);
    }

    @PostMapping("/register")
    public ResultUtil register(@RequestBody People people) {
        people.setImg("https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3386247472,87720242&fm=26&gp=0.jpg");
        try {
            iStudnetService.register(people);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil(ResultCodeUtil.FAIL, e.getMessage());
        }
        return new ResultUtil(ResultCodeUtil.SUCCESS);
    }
}
