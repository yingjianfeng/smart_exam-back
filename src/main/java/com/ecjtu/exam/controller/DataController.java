package com.ecjtu.exam.controller;

import com.ecjtu.exam.pojo.Data;
import com.ecjtu.exam.service.IDataService;
import com.ecjtu.exam.util.QiniuUtil;
import com.ecjtu.exam.util.ResultCodeUtil;
import com.ecjtu.exam.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/data")
public class DataController extends BaseController {
    @Autowired
    IDataService iDataService;
    @Autowired
    QiniuUtil qiniuUtil;

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

    @PostMapping("/deleteFile")
    public ResultUtil deleteFile(@RequestBody Data data) {
        System.out.println("data: " + data);
        try {
            iDataService.deleteById(data);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil(ResultCodeUtil.FAIL);
        }
        return new ResultUtil(ResultCodeUtil.SUCCESS, data);
    }

    @PostMapping("/fileUpload")
    public ResultUtil fileUpload(@RequestParam("file") MultipartFile file) {
        System.out.println("fileUpload开始了。。。");
        String filename = "";
        try {
            FileInputStream inputStream = (FileInputStream) file.getInputStream();
            filename = qiniuUtil.uploadByStream(inputStream);
            System.out.println("filename。。。" + filename);
            // 获取原文件名
            String str = file.getOriginalFilename();
            int lastIndexOf = str.lastIndexOf('.');
            Data data = new Data(super.id, filename, "未添加描述哦~~~", new Date(), "." + str.substring(str.length() - lastIndexOf - 1));
            iDataService.insert(data);
//            url = qiniuUtil.getUrl(filename);
//            System.out.println(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("imgUpload");
        return new ResultUtil(ResultCodeUtil.SUCCESS, filename);
    }


    @PostMapping("/updateDesById")
    public ResultUtil updateDesById(@RequestBody Data data) {
        try {
            iDataService.updateDesById(data);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil(ResultCodeUtil.FAIL);
        }
        return new ResultUtil(ResultCodeUtil.SUCCESS);
    }

}
