package com.ecjtu.exam.service.impl;

import com.ecjtu.exam.dao.IPeopleDao;
import com.ecjtu.exam.pojo.People;
import com.ecjtu.exam.service.IPeopleService;
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

    @Override
    public People login(People people) throws Exception {
        People res = ipeopleDao.qryByAccountAndType(people);
        if(res==null){
            throw new Exception("账号不存在");
        }
        if(!res.getPassword().equals(people.getPassword())){
            throw new Exception("密码错误");
        }
        return res;
    }
}
