package com.ecjtu.exam.service.impl;

import com.ecjtu.exam.dao.IDataDao;
import com.ecjtu.exam.dao.IPeopleDao;
import com.ecjtu.exam.pojo.Data;
import com.ecjtu.exam.pojo.People;
import com.ecjtu.exam.service.IDataService;
import com.ecjtu.exam.util.QiniuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImpl implements IDataService {
    @Autowired
    IDataDao iDataDao;
    @Autowired
    IPeopleDao iPeopleDao;
    @Autowired
    QiniuUtil qiniuUtil;
    @Override
    public List<Data> qryAll() throws Exception {
        List<Data> data = iDataDao.qryAll();
        for(Data d:data){
            People people = iPeopleDao.qryById(d.getP_id());
            d.setPname(people.getName());
            String url = qiniuUtil.getUrl(d.getName())+"?attname="+d.getName()+d.getType();
            d.setUrl(url);
        }
        return data;
    }
}
