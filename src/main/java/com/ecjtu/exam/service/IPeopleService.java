package com.ecjtu.exam.service;

import com.ecjtu.exam.pojo.People;

import java.util.List;

public interface IPeopleService {
    public List<People> qryAll();
    public People login(People people) throws Exception;
}
