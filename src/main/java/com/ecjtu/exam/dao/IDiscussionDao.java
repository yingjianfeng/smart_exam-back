package com.ecjtu.exam.dao;


import com.ecjtu.exam.pojo.Discussion;
import com.ecjtu.exam.pojo.PeopleLike;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IDiscussionDao {
    /**
     * 增加一条动态
     */
    void insert(Discussion discussion);

    /**
     * 查询所有的一级动态
     */
    List<Discussion> qryNotParentId();

    /**
     * 根据用户id   和 动态id 查点赞表
     */
    PeopleLike qryByPeopleIdAndDiscussionId(@Param("peopleId") int peopleId, @Param("discussionId") int discussionId);

    /**
     * 根据discussionId查出所有
     */
    List<PeopleLike> qryAllByDiscussionId(int id);

    /**
     * 根据people_id  和 iscussionId 删除peoplelike表
     */
    void deleteByPeopleIdAndDiscussionId(@Param("peopleId") int peopleId, @Param("discussionId") int discussionId);


    /**
     * 根据people_id  和 iscussionId 添加peoplelike表
     */
    void addByPeopleIdAndDiscussionId(@Param("peopleId") int peopleId, @Param("discussionId") int discussionId);

    /**
     * 查询有父id的  即评论的回复
     */
    List<Discussion> qryByParentId(int id);

}
