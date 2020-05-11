package com.ecjtu.exam.service.impl;

import com.ecjtu.exam.dao.IQuestionDao;
import com.ecjtu.exam.pojo.*;
import com.ecjtu.exam.pojo.vo.*;
import com.ecjtu.exam.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

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
    public void insertQuestions(QuestionAnswer questionAnswer, int id) throws Exception {
        iQuestionDao.insertQuestions(questionAnswer);
        if (questionAnswer.getSuccess() == 2) { //本题回答错误  加入错误表
            iQuestionDao.insertQuestionFail(questionAnswer.getQ_id(), id);
        }
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

    @Override
    public Set<QuestionInfo> qryQuestionFailByPId(int id) throws Exception {
        return iQuestionDao.qryQuestionFailByPId(id);
    }

    @Override
    public void deleteQuestionFailById(int id) throws Exception {
        iQuestionDao.deleteQuestionFailById(id);
    }

    @Override
    public List<QuestionGroup> qryQuestionInfoByGroup(int id) throws Exception {
        return iQuestionDao.qryQuestionInfoByGroup(id);
    }

    @Override
    public List<Echarts1> qryEcharts1() throws Exception {
        return iQuestionDao.qryEcharts1();
    }
    @Override
    public List<Echarts2> qryEcharts2() throws Exception {
        return iQuestionDao.qryEcharts2();
    }

    @Override
    public List<Echarts3> qryEcharts3(Date date,String name) throws Exception {
        Calendar c = Calendar.getInstance();
        List<Echarts3> list = new ArrayList<>();
        for(int i=1;i>-6;i--){
            c.setTime(date);
            c.add(Calendar.DAY_OF_MONTH, i);
            Date d = c.getTime();//这是明天
            String s = new SimpleDateFormat("yyyy-MM-dd").format(d);
            Echarts3 item = iQuestionDao.qryEcharts3(name,s);
            item.setDate(s);
            list.add(item);
        }
        return list;
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
