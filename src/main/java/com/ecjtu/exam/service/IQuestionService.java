package com.ecjtu.exam.service;

import com.ecjtu.exam.pojo.*;
import com.ecjtu.exam.pojo.vo.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IQuestionService {
    List<Grade> qryGradeAndClassify() throws Exception;

    Set<Question> qryByclassifyIdAndGradeId(int classify_id, int grade_id) throws Exception;

    Set<Question> qryByGradeId(int grade_id);

    /**
     * 记录一次答题
     */
    void insertQGroup(QuestionAnswerGroup questionAnswerGroup) throws Exception;

    /**
     * 更新答题正确率
     *
     * @param questionAnswerGroup
     * @throws Exception
     */
    void updateQGroupAccuracyById(QuestionAnswerGroup questionAnswerGroup) throws Exception;

    List<QuestionAnswerGroup> qryQGroupByPid(int id) throws Exception;

    /**
     * 记录答题卡
     */
    void insertQuestions(QuestionAnswer questionAnswer, int id) throws Exception;

    Question qryById(int id) throws Exception;

    void insertByQidAndPid(int qid, int pid) throws Exception;

    void deleteByQidAndPid(int qid, int pid) throws Exception;

    List<GroupAndAnswer> qryByGid(int id) throws Exception;

    void updateQGroupRemarkById(int id, String remark) throws Exception;

    List<QuestionInfo> qryAllQuestionInfo() throws Exception;

    QuestionInfo qryQuestionInfoById(int id) throws Exception;

    void updateQuestionInfoById(QuestionInfo questionInfo) throws Exception;

    void deleteQuestionInfoById(int id) throws Exception;

    List<QuestionClassify> qryQClassify() throws Exception;

    void addQuestion(Question question) throws Exception;

    List<PeopleQuestionDetail> qryPeopleQuestionDetail() throws Exception;

    Set<QuestionInfo> qryQuestionFailByPId(int id) throws Exception;

    void deleteQuestionFailById(int id) throws Exception;

    List<QuestionGroup> qryQuestionInfoByGroup(int id) throws Exception;

    List<Echarts1> qryEcharts1() throws Exception;

    List<Echarts2> qryEcharts2() throws Exception;

    List<Echarts3> qryEcharts3(Date date,String name) throws Exception;
}
