package com.ecjtu.exam.dao;


import com.ecjtu.exam.pojo.Discussion;

import java.util.List;

public interface IDiscussionDao {
    /**
     * 增加一条动态
     */
     void insert(Discussion discussion);

    /**
     *查询所有的一级动态
     */
    List<Discussion> qryNotParentId();

    /**
     * 查询id
     */
}
