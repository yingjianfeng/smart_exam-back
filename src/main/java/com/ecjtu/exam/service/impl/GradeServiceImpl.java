package com.ecjtu.exam.service.impl;

import com.ecjtu.exam.dao.IGradeDao;
import com.ecjtu.exam.pojo.Grade;
import com.ecjtu.exam.service.IGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements IGradeService {
    @Autowired
    IGradeDao iGradeDao;

    @Override
    public List<Grade> qryAll() {
        return iGradeDao.qryAll();
    }
}
