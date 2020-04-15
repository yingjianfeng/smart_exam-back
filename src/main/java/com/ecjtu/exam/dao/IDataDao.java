package com.ecjtu.exam.dao;

import com.ecjtu.exam.pojo.Data;

import java.util.List;

public interface IDataDao {
    List<Data> qryAll();

    void deleteById(int id);

    void insert(Data data);

    void updateDesById(Data data);
}
