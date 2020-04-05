package com.ecjtu.exam.service.impl;

import com.ecjtu.exam.dao.IPeopleDao;
import com.ecjtu.exam.pojo.People;
import com.ecjtu.exam.service.IPeopleService;
import com.ecjtu.exam.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleServiceImpl implements IPeopleService {
    @Autowired
    IPeopleDao ipeopleDao;
    @Override
    public List<People> qryAll() {
        return ipeopleDao.qryAll();
    }

    @Autowired
    RedisUtil redisUtil;

    @Override
    public People login(People people) throws Exception {
        People res = ipeopleDao.qryByAccount(people.getAccount());
        if(res==null){
            throw new Exception("用户不存在");
        }
        if(!res.getPassword().equals(people.getPassword())){
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
        try{
            People qryPeople = ipeopleDao.qryByAccountAndType(people);
            if(qryPeople!=null){
                throw new Exception("账号已经被注册");
            }
            ipeopleDao.insert(people);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public People qryById(int id) throws Exception {
        return ipeopleDao.qryById(id);
    }
}
