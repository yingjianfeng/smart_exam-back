package com.ecjtu.exam.service.impl;

import com.ecjtu.exam.dao.ISchoolDao;
import com.ecjtu.exam.pojo.School;
import com.ecjtu.exam.service.ISchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SchoolServiceImpl  implements ISchoolService {
    @Autowired
    ISchoolDao iSchoolDao;
    @Override
    public List<School> qryAll() {
        return iSchoolDao.qryAll();
    }
}
