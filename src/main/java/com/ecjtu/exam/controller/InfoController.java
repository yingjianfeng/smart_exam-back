package com.ecjtu.exam.controller;

import com.ecjtu.exam.service.IPeopleService;
import com.ecjtu.exam.util.QiniuUtil;
import com.ecjtu.exam.util.ResultCodeUtil;
import com.ecjtu.exam.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/info")
public class InfoController extends BaseController {
    @Autowired
    IPeopleService iPeopleService;
    @Autowired
    QiniuUtil qiniuUtil;
    @PostMapping("/qryInfo")
    public ResultUtil qryInfo() {
        Map map = null;
        try {
            map = iPeopleService.qryInfo(super.id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultUtil(ResultCodeUtil.SUCCESS, map);
    }

    @PostMapping("/updatePassword")
    public ResultUtil updatePassword(@RequestBody Map<String, String> map) {
        String oldPwd = map.get("oldPwd");
        String newPwd = map.get("newPwd");
        System.out.println("oldPwd"+oldPwd);
        System.out.println("newPwd"+newPwd);
        Map res = new HashMap<String, String>();
        try {
            if(!oldPwd.equals(super.password)){
                return new ResultUtil( ResultCodeUtil.OLDPWD_ERROR);
            }
            iPeopleService.UpdatePwdById(newPwd,super.id);
            return new ResultUtil( ResultCodeUtil.UPDATEPWD_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultUtil( ResultCodeUtil.UPDATEPWD_ERROR);
    }

    @PostMapping("/updateImg")
    public ResultUtil updateImg(@RequestParam("file") MultipartFile file) {
        String filename = "";
        System.out.println("updateImg");
        try {
            FileInputStream inputStream = (FileInputStream) file.getInputStream();
            filename = qiniuUtil.uploadByStream(inputStream);
            iPeopleService.UpdateImgById(qiniuUtil.getUrl(filename),super.id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("imgUpload");
        return new ResultUtil(ResultCodeUtil.SUCCESS, qiniuUtil.getUrl(filename));
    }





}
