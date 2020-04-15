package com.ecjtu.exam.service.impl;

import com.ecjtu.exam.dao.IQuestionDao;
import com.ecjtu.exam.pojo.*;
import com.ecjtu.exam.pojo.vo.GroupAndAnswer;
import com.ecjtu.exam.pojo.vo.PeopleQuestionDetail;
import com.ecjtu.exam.pojo.vo.QuestionInfo;
import com.ecjtu.exam.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class QuestionServiceImpl implements IQuestionService {
    @Autowired
    IQuestionDao iQuestionDao;

    @Override
    public List<Grade> qryGradeAndClassify() throws Exception {
        return iQuestionDao.qryGradeAndClassify();
    }

    @Override
    public Set<Question> qryByclassifyIdAndGradeId(int classify_id, int grade_id) throws Exception {
        return setNull(iQuestionDao.qryByclassifyIdAndGradeId(classify_id, grade_id));
    }

    @Override
    public Set<Question> qryByGradeId(int grade_id) {
        return setNull(iQuestionDao.qryByGradeId(grade_id));
    }

    @Override
    public void insertQGroup(QuestionAnswerGroup questionAnswerGroup) throws Exception {
        iQuestionDao.insertQGroup(questionAnswerGroup);
    }

    @Override
    public void updateQGroupAccuracyById(QuestionAnswerGroup questionAnswerGroup) throws Exception {
        iQuestionDao.updateQGroupAccuracyById(questionAnswerGroup);
    }

    @Override
    public List<QuestionAnswerGroup> qryQGroupByPid(int id) throws Exception {
        return iQuestionDao.qryQGroupByPid(id);
    }

    @Override
    public void insertQuestions(QuestionAnswer questionAnswer) throws Exception {
        iQuestionDao.insertQuestions(questionAnswer);
    }

    @Override
    public Question qryById(int id) throws Exception {
        return iQuestionDao.qryById(id);
    }

    @Override
    public void insertByQidAndPid(int qid, int pid) throws Exception {
        iQuestionDao.insertByQidAndPid(qid, pid);
    }

    @Override
    public void deleteByQidAndPid(int qid, int pid) throws Exception {
        iQuestionDao.deleteByQidAndPid(qid, pid);
    }

    @Override
    public List<GroupAndAnswer> qryByGid(int id) throws Exception {
        return iQuestionDao.qryByGid(id);
    }

    @Override
    public void updateQGroupRemarkById(int id, String remark) throws Exception {
        iQuestionDao.updateQGroupRemarkById(id, remark);
    }

    @Override
    public List<QuestionInfo> qryAllQuestionInfo() throws Exception {
        return iQuestionDao.qryAllQuestionInfo();
    }

    @Override
    public QuestionInfo qryQuestionInfoById(int id) throws Exception {
        return iQuestionDao.qryQuestionInfoById(id);
    }

    @Override
    public void updateQuestionInfoById(QuestionInfo questionInfo) throws Exception {
        iQuestionDao.updateQuestionInfoById(questionInfo);
    }

    @Override
    public void deleteQuestionInfoById(int id) throws Exception {
        iQuestionDao.deleteQuestionInfoById(id);
    }

    @Override
    public List<QuestionClassify> qryQClassify() throws Exception {
        return iQuestionDao.qryQClassify();
    }

    @Override
    public void addQuestion(Question question) throws Exception {
        iQuestionDao.addQuestion(question);
    }

    @Override
    public List<PeopleQuestionDetail> qryPeopleQuestionDetail() throws Exception {
        return iQuestionDao.qryPeopleQuestionDetail();
    }

    //把question里的答案置空
    public Set<Question> setNull(Set<Question> questions) {
        for (Question question : questions) {
            question.setAnswer(null);
        }
        return questions;
    }

    //把question里的答案置空
    public Set<Question> getSize(Set<Question> questions) {
        Set<Question> res = new HashSet<Question>();
        int size = 0;
        for (Question question : questions) {
            question.setAnswer(null);
            res.add(question);
            if (size == 10) {
                break;
            }
        }
        return res;
    }


}
