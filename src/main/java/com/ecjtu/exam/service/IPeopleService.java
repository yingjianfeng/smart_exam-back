package com.ecjtu.exam.service;

import com.ecjtu.exam.pojo.People;

import java.util.List;
import java.util.Map;

public interface IPeopleService {
    public List<People> qryAll();

    public People login(People people) throws Exception;

    public void register(People people) throws Exception;

    public People qryById(int id) throws Exception;

    public Map qryInfo(int id) throws Exception;

    public void UpdatePwdById(String password, int id) throws Exception;

    public void UpdateImgById( String img, int id)throws Exception;
}
