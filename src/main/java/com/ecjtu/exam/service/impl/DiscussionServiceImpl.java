package com.ecjtu.exam.service.impl;

import com.ecjtu.exam.dao.IDiscussionDao;
import com.ecjtu.exam.dao.IQuestionDao;
import com.ecjtu.exam.pojo.Discussion;
import com.ecjtu.exam.pojo.PeopleLike;
import com.ecjtu.exam.pojo.QuestionAnswerGroup;
import com.ecjtu.exam.service.IDiscussionService;
import com.ecjtu.exam.service.IPeopleService;
import com.ecjtu.exam.util.QiniuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussionServiceImpl implements IDiscussionService {
    @Autowired
    IDiscussionDao iDiscussionDao;
    @Autowired
    QiniuUtil qiniuUtil;
    @Autowired
    IQuestionDao iQuestionDao;

    @Autowired
    IPeopleService iPeopleService;

    @Override
    public void insert(Discussion discussion) throws Exception {
        try {
            iDiscussionDao.insert(discussion);
        } catch (Exception e) {
            throw new Exception("增加一条动态失败！" + e.getMessage());
        }
    }

    @Override
    public List<Discussion> qryNotParentId(int id) throws Exception {
        PeopleLike peopleLike = null;
        List<Discussion> discussions = iDiscussionDao.qryNotParentId();
        for (Discussion discussion : discussions) {
            discussion.setImgs(qiniuUtil.getUrl(discussion.getImgs()));
            /*查看当前用户是否点过赞*/
            peopleLike = qryByPeopleIdAndDiscussionId(id, discussion.getId());
            if (peopleLike == null) {
                discussion.setLiked(false);
            } else {
                discussion.setLiked(true);
            }
            /*查看点赞的人数*/
            List<PeopleLike> peopleLikes = qryAllByDiscussionId(discussion.getId());
            discussion.setLike(peopleLikes.size());

            /*获取评论数*/
            discussion.setComment(qryByParentId(discussion.getId(), id).size());
        }
        return discussions;
    }

    @Override
    public PeopleLike qryByPeopleIdAndDiscussionId(int peopleId, int discussionId) throws Exception {
        return iDiscussionDao.qryByPeopleIdAndDiscussionId(peopleId, discussionId);
    }

    @Override
    public List<PeopleLike> qryAllByDiscussionId(int id) throws Exception {
        return iDiscussionDao.qryAllByDiscussionId(id);
    }

    @Override
    public void deleteByPeopleIdAndDiscussionId(int peopleId, int discussionId) throws Exception {
        iDiscussionDao.deleteByPeopleIdAndDiscussionId(peopleId, discussionId);
    }

    @Override
    public void addByPeopleIdAndDiscussionId(int peopleId, int discussionId) throws Exception {
        iDiscussionDao.addByPeopleIdAndDiscussionId(peopleId, discussionId);
    }

    @Override
    public List<Discussion> qryByParentId(int parent_id, int id) throws Exception {
        List<Discussion> discussions = iDiscussionDao.qryByParentId(parent_id);
        PeopleLike peopleLike = null;
        for (Discussion discussion : discussions) {
            discussion.setImgs(qiniuUtil.getUrl(discussion.getImgs()));
            /*查看当前用户是否点过赞*/
            peopleLike = qryByPeopleIdAndDiscussionId(id, discussion.getId());
            if (peopleLike == null) {
                discussion.setLiked(false);
            } else {
                discussion.setLiked(true);
            }
            /*查看点赞的人数*/
            List<PeopleLike> peopleLikes = qryAllByDiscussionId(discussion.getId());
            discussion.setLike(peopleLikes.size());
        }
        return discussions;
    }

    @Override
    public List<QuestionAnswerGroup> groupByPIdQry() throws Exception {
        return iQuestionDao.groupByPIdQry();
    }
}
