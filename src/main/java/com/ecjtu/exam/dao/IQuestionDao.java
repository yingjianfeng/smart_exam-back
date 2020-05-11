package com.ecjtu.exam.dao;

import com.ecjtu.exam.pojo.*;
import com.ecjtu.exam.pojo.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface IQuestionDao {
    List<Grade> qryGradeAndClassify();

    Set<Question> qryByclassifyIdAndGradeId(@Param("classify_id") int classify_id, @Param("grade_id") int grade_id);

    Set<Question> qryByGradeId(int grade_id);

    /**
     * QuestionClassify
     * 记录一次答题
     */
    void insertQGroup(QuestionAnswerGroup questionAnswerGroup);

    /**
     * 更新答题正确率
     *
     * @param questionAnswerGroup
     */
    void updateQGroupAccuracyById(QuestionAnswerGroup questionAnswerGroup);

    /**
     * 通过id查答题记录
     *
     * @param id
     * @return
     */
    List<QuestionAnswerGroup> qryQGroupByPid(int id);

    /**
     * 记录答题卡
     */
    void insertQuestions(QuestionAnswer questionAnswer);

    /**
     * 通过id查题目
     *
     * @param id
     * @return
     */
    Question qryById(int id);

    List<QuestionAnswerGroup> groupByPIdQry();

    void insertByQidAndPid(@Param("qid") int qid, @Param("pid") int pid);

    void deleteByQidAndPid(@Param("qid") int qid, @Param("pid") int pid);

    List<GroupAndAnswer> qryByGid(int id);

    void updateQGroupRemarkById(@Param("id") int id, @Param("remark") String remark);


    List<QuestionInfo> qryAllQuestionInfo();

    QuestionInfo qryQuestionInfoById(int id);

    void updateQuestionInfoById(QuestionInfo questionInfo);

    void deleteQuestionInfoById(int id) throws Exception;

    List<QuestionClassify> qryQClassify();

    void addQuestion(Question question);

    List<PeopleQuestionDetail> qryPeopleQuestionDetail();

    void insertQuestionFail(@Param("q_id") int q_id, @Param("p_id") int p_id);

    Set<QuestionInfo> qryQuestionFailByPId(int id);

    void deleteQuestionFailById(int id);

    List<QuestionGroup> qryQuestionInfoByGroup(int id);

    List<Echarts1> qryEcharts1();

    List<Echarts2> qryEcharts2();
    Echarts3 qryEcharts3( @Param("name") String name, @Param("date") String date);
}
