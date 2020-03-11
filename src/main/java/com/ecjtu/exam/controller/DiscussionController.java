package com.ecjtu.exam.controller;

import com.alibaba.fastjson.JSON;
import com.ecjtu.exam.pojo.Discussion;
import com.ecjtu.exam.service.IDiscussionService;
import com.ecjtu.exam.util.QiniuUtil;
import com.ecjtu.exam.util.ResultCodeUtil;
import com.ecjtu.exam.util.ResultUtil;
import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/discussion")
public class DiscussionController extends BaseController {

    @Autowired
    QiniuUtil qiniuUtil;
    @Autowired
    IDiscussionService iDiscussionService;

    @PostMapping("/imgUpload")
    public ResultUtil imgUpload(@RequestParam("file") MultipartFile file) {
        String filename = "";
        try {
            FileInputStream inputStream = (FileInputStream) file.getInputStream();
            filename = qiniuUtil.uploadByStream(inputStream);
//            url = qiniuUtil.getUrl(filename);
//            System.out.println(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("imgUpload");
        return new ResultUtil(ResultCodeUtil.SUCCESS, filename);
    }

    @PostMapping("/imgDelete")
    public void imgDelete(@RequestBody Map<String, String> map) {
        String filename = map.get("filename");
        System.out.println("filename: " + filename);
        try {
            qiniuUtil.delete(filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/insert")
    public void insert(@RequestBody Discussion discussion) {
        try {
            discussion.setPeople_id(this.id);
            discussion.setState(1);
            discussion.setCreate_time(new Date());
            iDiscussionService.insert(discussion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/qryNotParentId")
    public ResultUtil qryNotParentId() {
        List<Discussion> discussions = null;
        try {
            discussions = iDiscussionService.qryNotParentId();
            for(Discussion discussion:discussions){
                discussion.setImgs(qiniuUtil.getUrl(discussion.getImgs()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultUtil(ResultCodeUtil.SUCCESS, discussions);
    }


}
