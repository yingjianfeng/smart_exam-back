package com.ecjtu.exam.controller;

import com.ecjtu.exam.pojo.Data;
import com.ecjtu.exam.service.IDataService;
import com.ecjtu.exam.util.QiniuUtil;
import com.ecjtu.exam.util.ResultCodeUtil;
import com.ecjtu.exam.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data")
public class DataController {
    @Autowired
    IDataService iDataService;



    @PostMapping("/qryAll")
    public ResultUtil qryAll() {
        List<Data> data = null;
        try {
            data = iDataService.qryAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultUtil(ResultCodeUtil.SUCCESS, data);
    }
}
