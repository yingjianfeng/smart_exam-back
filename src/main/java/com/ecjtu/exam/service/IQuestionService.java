package com.ecjtu.exam.service;

import com.ecjtu.exam.pojo.Grade;
import com.ecjtu.exam.pojo.Question;
import com.ecjtu.exam.pojo.QuestionAnswer;
import com.ecjtu.exam.pojo.QuestionAnswerGroup;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface IQuestionService {
    List<Grade> qryGradeAndClassify() throws Exception;

    Set<Question> qryByclassifyIdAndGradeId(int classify_id, int grade_id) throws Exception;

    /**
     * 记录一次答题
     */
    void insertQGroup(QuestionAnswerGroup questionAnswerGroup)throws Exception;

    /**
     * 记录答题卡
     */
    void insertQuestions(QuestionAnswer questionAnswer)throws Exception;

    Question qryById(int id)throws Exception;
}
