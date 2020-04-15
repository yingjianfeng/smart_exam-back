package com.ecjtu.exam.service.impl;

import com.ecjtu.exam.dao.IGradeDao;
import com.ecjtu.exam.dao.IPeopleDao;
import com.ecjtu.exam.dao.IQuestionDao;
import com.ecjtu.exam.dao.ISchoolDao;
import com.ecjtu.exam.pojo.Grade;
import com.ecjtu.exam.pojo.People;
import com.ecjtu.exam.pojo.QuestionAnswerGroup;
import com.ecjtu.exam.pojo.School;
import com.ecjtu.exam.service.IPeopleService;
import com.ecjtu.exam.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PeopleServiceImpl implements IPeopleService {
    @Autowired
    IPeopleDao ipeopleDao;

    @Autowired
    IGradeDao iGradeDao;

    @Autowired
    ISchoolDao iSchoolDao;

    @Autowired
    IQuestionDao iQuestionDao;

    @Override
    public List<People> qryAll() {
        return ipeopleDao.qryAll();
    }

    @Autowired
    RedisUtil redisUtil;

    @Override
    public People login(People people) throws Exception {
        People res = ipeopleDao.qryByAccount(people.getAccount());
        if (res == null) {
            throw new Exception("用户不存在");
        }
        if (!res.getPassword().equals(people.getPassword())) {
            throw new Exception("密码错误");
        }
//        try{
//            redisUtil.set(res);
//        }catch (Exception e){
//            throw new Exception("写入缓存失败"+e);
//        }
//        System.out.println("写入缓存成功");
        return res;
    }

    @Override
    public void register(People people) throws Exception {
        try {
            People qryPeople = ipeopleDao.qryByAccountAndType(people);
            if (qryPeople != null) {
                throw new Exception("账号已经被注册");
            }
            ipeopleDao.insert(people);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public People qryById(int id) throws Exception {
        return ipeopleDao.qryById(id);
    }

    @Override
    public Map qryInfo(int id) throws Exception {
        People people = ipeopleDao.qryById(id);
        Map map = new HashMap();
        Grade grade = iGradeDao.qryById(people.getGrade_id());
        School school = iSchoolDao.qryById(people.getSchool_id());
        List<QuestionAnswerGroup> questionAnswerGroups = iQuestionDao.qryQGroupByPid(id);
        String time = computeTime(questionAnswerGroups);
        map.put("account", people.getAccount());
        map.put("name", people.getName());
        map.put("phone", people.getPhone());
        map.put("grade", grade.getName());
        map.put("school", school.getName());
        map.put("img", people.getImg());
        map.put("time", time);
        return map;
    }

    @Override
    public void UpdatePwdById(String password, int id) throws Exception {
        ipeopleDao.UpdatePwdById(password, id);
    }

    @Override
    public void UpdateImgById(String img, int id) throws Exception {
        ipeopleDao.UpdateImgById(img, id);
    }

    public String computeTime(List<QuestionAnswerGroup> questionAnswerGroups) {
        int hour = 0;
        int minute = 0;
        int second = 0;

        for (QuestionAnswerGroup item : questionAnswerGroups) {
            String spentTime = item.getSpentTime();
            if (spentTime.equals("不计时")) {
                continue;
            }
            int a = spentTime.indexOf('分');
            int b = spentTime.indexOf('秒');
            int c = Integer.parseInt(spentTime.substring(0, a));
            int d = Integer.parseInt(spentTime.substring(a + 1, b));
            minute += c;
            second += d;
        }
        if (second > 59) {
            minute = minute + (second / 60);
            second = second % 60;
        }
        if (minute > 59) {
            hour = hour + (minute / 60);
            minute = minute % 60;
        }

        return hour + "时" + minute + "分" + second + "秒";

    }


}
