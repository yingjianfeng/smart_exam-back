package com.ecjtu.exam.service.impl;

import com.ecjtu.exam.dao.IQuestionDao;
import com.ecjtu.exam.pojo.Grade;
import com.ecjtu.exam.pojo.Question;
import com.ecjtu.exam.pojo.QuestionAnswer;
import com.ecjtu.exam.pojo.QuestionAnswerGroup;
import com.ecjtu.exam.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public void insertQGroup(QuestionAnswerGroup questionAnswerGroup) throws Exception{
        iQuestionDao.insertQGroup(questionAnswerGroup);
    }

    @Override
    public void insertQuestions(QuestionAnswer questionAnswer) throws Exception {
        iQuestionDao.insertQuestions(questionAnswer);
    }

    @Override
    public Question qryById(int id) throws Exception {
        return iQuestionDao.qryById(id);
    }

    //把question里的答案置空
    public Set<Question> setNull(Set<Question> questions){
        for(Question question: questions){
            question.setAnswer(null);
        }
        return questions;
    }


}
