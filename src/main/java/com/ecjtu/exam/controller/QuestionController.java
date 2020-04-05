package com.ecjtu.exam.controller;

import com.ecjtu.exam.pojo.Grade;
import com.ecjtu.exam.pojo.Question;
import com.ecjtu.exam.pojo.QuestionAnswer;
import com.ecjtu.exam.pojo.QuestionAnswerGroup;
import com.ecjtu.exam.service.IQuestionService;
import com.ecjtu.exam.util.ResultCodeUtil;
import com.ecjtu.exam.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/question")
public class QuestionController extends BaseController{
    @Autowired
    IQuestionService iQuestionService;

    @GetMapping("/qryGradeAndClassify")
    public ResultUtil qryGradeAndClassify() {
        List<Grade> grades;
        try {
            grades = iQuestionService.qryGradeAndClassify();
        } catch (Exception e) {
            return new ResultUtil(ResultCodeUtil.FAIL);
        }
        return new ResultUtil(ResultCodeUtil.SUCCESS,grades);
    }

    @PostMapping("/qryByclassifyIdAndGraderId")
    public ResultUtil qryByclassifyIdAndGraderId(@RequestBody Map<String, Integer> map) {
        Set<Question> questions;
        Integer classify_id = map.get("classify_id");
        Integer grade_id = map.get("grade_id");

        System.out.println("classify_id:"+classify_id+" grade_id:"+grade_id);
        try {
            questions = iQuestionService.qryByclassifyIdAndGradeId(classify_id, grade_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil(ResultCodeUtil.FAIL);
        }
        return new ResultUtil(ResultCodeUtil.SUCCESS,questions);
    }

    @PostMapping("/submitAnswer")
    public ResultUtil submitAnswer(@RequestBody Map<String,List<Question>> map) {
        List<Question> questions = map.get("questions");
        QuestionAnswerGroup questionAnswerGroup = new QuestionAnswerGroup(super.id,new Date());
        int times = 0;
        try {
            iQuestionService.insertQGroup(questionAnswerGroup);
            QuestionAnswer questionAnswer = new QuestionAnswer();
            for(Question question: questions){
                Question q = iQuestionService.qryById(question.getId());
                questionAnswer.setSuccess(2);
                questionAnswer.setG_id(questionAnswerGroup.getId());
                questionAnswer.setAnswer(question.getAnswer());
                questionAnswer.setQ_id(question.getId());
                if(q.getAnswer().equals(question.getAnswer())){
                    questionAnswer.setSuccess(1);
                    times++;
                }
                question.setAnswer(q.getAnswer());
                iQuestionService.insertQuestions(questionAnswer);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil(ResultCodeUtil.FAIL);
        }
        Map resultMap = new HashMap();
        resultMap.put("number",times);

        Map qmap = new HashMap();
        for(Question q:questions){
            qmap.put(q.getId(),q);
        }
        resultMap.put("map",qmap);
        return new ResultUtil(ResultCodeUtil.SUCCESS,resultMap);
    }

}
