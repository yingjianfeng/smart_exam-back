package com.ecjtu.exam.dao;

import com.ecjtu.exam.pojo.Grade;
import com.ecjtu.exam.pojo.Question;
import com.ecjtu.exam.pojo.QuestionAnswer;
import com.ecjtu.exam.pojo.QuestionAnswerGroup;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface IQuestionDao {
    List<Grade> qryGradeAndClassify();

    Set<Question> qryByclassifyIdAndGradeId(@Param("classify_id") int classify_id, @Param("grade_id") int grade_id);

    /**
     * 记录一次答题
     */
    void insertQGroup(QuestionAnswerGroup questionAnswerGroup);

    /**
     * 记录答题卡
     */
    void insertQuestions(QuestionAnswer questionAnswer);

    /**
     * 通过id查题目
     * @param id
     * @return
     */
    Question qryById(int id);
}
