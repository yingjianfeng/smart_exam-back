package com.ecjtu.exam.service.impl;

import com.ecjtu.exam.dao.IDiscussionDao;
import com.ecjtu.exam.pojo.Discussion;
import com.ecjtu.exam.service.IDiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussionServiceImpl implements IDiscussionService {
    @Autowired
    IDiscussionDao iDiscussionDao;

    @Override
    public void insert(Discussion discussion) throws Exception {
        try {
            iDiscussionDao.insert(discussion);
        } catch (Exception e) {
            throw new Exception("增加一条动态失败！" + e.getMessage());
        }
    }

    @Override
    public List<Discussion> qryNotParentId() throws Exception {
        return iDiscussionDao.qryNotParentId();
    }
}
