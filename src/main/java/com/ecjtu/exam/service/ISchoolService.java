package com.ecjtu.exam.service;

import com.ecjtu.exam.pojo.School;

import java.util.List;

public interface ISchoolService {
    List<School> qryAll() throws Exception;
}
