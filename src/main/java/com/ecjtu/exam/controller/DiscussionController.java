package com.ecjtu.exam.controller;

import com.alibaba.fastjson.JSON;
import com.ecjtu.exam.dao.IQuestionDao;
import com.ecjtu.exam.pojo.Discussion;
import com.ecjtu.exam.pojo.People;
import com.ecjtu.exam.pojo.PeopleLike;
import com.ecjtu.exam.pojo.QuestionAnswerGroup;
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
import java.util.HashMap;
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

    @PostMapping("/reply")
    public void reply(@RequestBody Discussion discussion) {
        System.out.println(discussion);
        discussion.setPeople_id(this.id);
        discussion.setState(1);
        discussion.setCreate_time(new Date());
        try {
            iDiscussionService.insertReply(discussion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/qryNotParentId")
    public ResultUtil qryNotParentId() {
        List<Discussion> discussions = null;
        PeopleLike peopleLike = null;
        try {
            discussions = iDiscussionService.qryNotParentId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        People people = new People();
        people.setId(super.id);
        people.setImg(super.img);

        Map<String, Object> map = new HashMap();
        map.put("people", people);
        map.put("discussions", discussions);
        return new ResultUtil(ResultCodeUtil.SUCCESS, map);
    }

    @GetMapping("/deleteLikeByDiscussion")
    public ResultUtil deleteLikeByDiscussion(int discussion_id) {
        try {
            iDiscussionService.deleteByPeopleIdAndDiscussionId(id, discussion_id);
        } catch (Exception e) {
            return new ResultUtil(ResultCodeUtil.FAIL);
        }
        return new ResultUtil(ResultCodeUtil.SUCCESS);
    }

    @GetMapping("/addLikeByDiscussion")
    public ResultUtil addLikeByDiscussion(int discussion_id) {
        try {
            iDiscussionService.addByPeopleIdAndDiscussionId(id, discussion_id);
        } catch (Exception e) {
            return new ResultUtil(ResultCodeUtil.FAIL);
        }
        return new ResultUtil(ResultCodeUtil.SUCCESS);
    }

    @GetMapping("/qryByParentId")
    public ResultUtil qryByParentId(int parent_id) {
        List<Discussion> comment = null;
        try {
            comment = iDiscussionService.qryByParentId(parent_id, id);
        } catch (Exception e) {
            return new ResultUtil(ResultCodeUtil.FAIL);
        }
        return new ResultUtil(ResultCodeUtil.SUCCESS, comment);
    }

    @GetMapping("/groupByPIdQry")
    public ResultUtil groupByPIdQry() {
        List<QuestionAnswerGroup> questionAnswerGroups = null;
        try {
            questionAnswerGroups = iDiscussionService.groupByPIdQry();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultUtil(ResultCodeUtil.SUCCESS, questionAnswerGroups);
    }

}
