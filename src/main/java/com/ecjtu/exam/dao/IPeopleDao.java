package com.ecjtu.exam.dao;

import com.ecjtu.exam.pojo.People;

import java.util.List;

public interface IPeopleDao {
    public List<People> qryAll();

    public People qryByAccountAndType(People people) throws Exception;
}
