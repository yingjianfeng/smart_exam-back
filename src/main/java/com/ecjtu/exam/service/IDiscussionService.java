package com.ecjtu.exam.service;

import com.ecjtu.exam.pojo.Discussion;

import java.util.List;

public interface IDiscussionService {
    void insert(Discussion discussion) throws Exception;

    List<Discussion> qryNotParentId() throws Exception;
}
