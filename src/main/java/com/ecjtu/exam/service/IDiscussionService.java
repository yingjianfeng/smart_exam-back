package com.ecjtu.exam.service;

import com.ecjtu.exam.pojo.Discussion;
import com.ecjtu.exam.pojo.PeopleLike;

import java.util.List;

public interface IDiscussionService {
    void insert(Discussion discussion) throws Exception;

    List<Discussion> qryNotParentId(int id) throws Exception;

    PeopleLike qryByPeopleIdAndDiscussionId(int peopleId, int discussionId) throws Exception;

    List<PeopleLike> qryAllByDiscussionId(int id) throws Exception;

    void deleteByPeopleIdAndDiscussionId(int peopleId, int discussionId) throws Exception;

    void addByPeopleIdAndDiscussionId(int peopleId, int discussionId) throws Exception;

    List<Discussion> qryByParentId(int parent_id,int id)throws Exception;
}
