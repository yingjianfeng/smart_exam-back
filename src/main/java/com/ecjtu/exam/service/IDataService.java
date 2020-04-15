package com.ecjtu.exam.service;

import com.ecjtu.exam.pojo.Data;

import java.util.List;

public interface IDataService {

    List<Data> qryAll() throws Exception;

    void deleteById(Data data) throws Exception;

    void insert(Data data) throws Exception;

    void updateDesById(Data data) throws Exception;
}
