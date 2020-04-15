package com.ecjtu.exam.dao;

import com.ecjtu.exam.pojo.Grade;

import java.util.List;

public interface IGradeDao {
    List<Grade> qryAll();

    Grade qryById(int id);

    List<Grade> qryAllGrade();
}
