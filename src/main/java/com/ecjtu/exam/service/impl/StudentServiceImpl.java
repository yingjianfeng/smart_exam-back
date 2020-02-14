package com.ecjtu.exam.service.impl;

import com.ecjtu.exam.dao.IStudnetDao;
import com.ecjtu.exam.pojo.Student;
import com.ecjtu.exam.service.IStudnetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudnetService {
    @Autowired
    IStudnetDao iStudnetDao;
    @Override
    public List<Student> qryAll() {
        return iStudnetDao.qryAll();
    }
}
