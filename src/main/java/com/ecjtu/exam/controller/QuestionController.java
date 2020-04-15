package com.ecjtu.exam.controller;

import com.ecjtu.exam.pojo.*;
import com.ecjtu.exam.pojo.vo.GroupAndAnswer;
import com.ecjtu.exam.pojo.vo.PeopleQuestionDetail;
import com.ecjtu.exam.pojo.vo.QuestionInfo;
import com.ecjtu.exam.service.IQuestionService;
import com.ecjtu.exam.util.ResultCodeUtil;
import com.ecjtu.exam.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/question")
public class QuestionController extends BaseController {
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
        return new ResultUtil(ResultCodeUtil.SUCCESS, grades);
    }

    @PostMapping("/qryByclassifyIdAndGraderId")
    public ResultUtil qryByclassifyIdAndGraderId(@RequestBody Map<String, Integer> map) {
        Set<Question> questions = null;
        Integer classify_id = map.get("classify_id");
        Integer grade_id = map.get("grade_id");

        System.out.println("classify_id:" + classify_id + " grade_id:" + grade_id);
        try {
            if (classify_id == -1) {
                //随机组试卷
                questions = iQuestionService.qryByGradeId(grade_id);
            } else {
                //单项训练
                questions = iQuestionService.qryByclassifyIdAndGradeId(classify_id, grade_id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil(ResultCodeUtil.FAIL);
        }
        return new ResultUtil(ResultCodeUtil.SUCCESS, questions);
    }

    public List<Question> transform(List<LinkedHashMap> questions) {
        List<Question> list = new ArrayList<Question>();
        Question question = null;
        for (LinkedHashMap map : questions) {
            question = new Question();
            question.setId((int) map.get("id"));
            question.setClassify_id((int) map.get("classify_id"));
            question.setType((int) map.get("type"));
            question.setGrader_id((int) map.get("grader_id"));
            question.setSchool_id((int) map.get("school_id"));
            question.setContent((String) map.get("content"));
            question.setOption1((String) map.get("option1"));
            question.setOption2((String) map.get("option2"));
            question.setOption3((String) map.get("option3"));
            question.setOption4((String) map.get("option4"));
            question.setAnswer((String) map.get("answer"));
            question.setCollect((int) map.get("collect"));
            list.add(question);
        }
        return list;
    }


    @PostMapping("/submitAnswer")
    public ResultUtil submitAnswer(@RequestBody Map<String, Object> map) {
        List<Question> questions = transform((List<LinkedHashMap>) map.get("questions"));
        Integer time = (Integer) map.get("time");
        StringBuilder sb = new StringBuilder();
        if (time == 0) {
            sb.append("不计时");
        } else {
            int minute = time / 60;
            int second = time % 60;
            sb.append(minute).append("分").append(second).append("秒");
        }


        int times = 0;
        try {
            QuestionAnswerGroup questionAnswerGroup = new QuestionAnswerGroup(super.id, new Date(), sb.toString());
            iQuestionService.insertQGroup(questionAnswerGroup);
            QuestionAnswer questionAnswer = new QuestionAnswer();
            for (Question question : questions) {
                Question q = iQuestionService.qryById(question.getId());
                questionAnswer.setSuccess(2);
                questionAnswer.setG_id(questionAnswerGroup.getId());
                questionAnswer.setAnswer(question.getAnswer());
                questionAnswer.setQ_id(question.getId());
                if (q.getAnswer().equals(question.getAnswer())) {
                    questionAnswer.setSuccess(1);
                    times++;
                }
                question.setAnswer(q.getAnswer());
                iQuestionService.insertQuestions(questionAnswer);
            }
            double accuracy = (times * 1.0) / questions.size();
            System.out.println("accuracy: " + accuracy + " times: " + times + " questions.size():" + questions.size());
            questionAnswerGroup.setAccuracy(accuracy);
            iQuestionService.updateQGroupAccuracyById(questionAnswerGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil(ResultCodeUtil.FAIL);
        }
        Map resultMap = new HashMap();
        resultMap.put("number", times);

        Map qmap = new HashMap();
        for (Question q : questions) {
            qmap.put(q.getId(), q);
        }
        resultMap.put("map", qmap);
        return new ResultUtil(ResultCodeUtil.SUCCESS, resultMap);
    }

    @PostMapping("/qryQGroupByPid")
    public ResultUtil qryQGroupByPid() {
        List<QuestionAnswerGroup> questionAnswerGroups = null;
        try {
            questionAnswerGroups = iQuestionService.qryQGroupByPid(super.id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil(ResultCodeUtil.FAIL);
        }
        return new ResultUtil(ResultCodeUtil.SUCCESS, questionAnswerGroups);
    }


    @PostMapping("/insertByQidAndPid")
    public ResultUtil insertByQidAndPid(@RequestBody Map<String, Integer> map) {
        Integer q_id = map.get("q_id");
        System.out.println("q_id: " + q_id);
        try {
            iQuestionService.insertByQidAndPid(q_id, super.id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil(ResultCodeUtil.FAIL);
        }
        return new ResultUtil(ResultCodeUtil.SUCCESS, null);
    }

    @PostMapping("/deleteByQidAndPid")
    public ResultUtil deleteByQidAndPid(@RequestBody Map<String, Integer> map) {
        Integer q_id = map.get("q_id");
        System.out.println("q_id: " + q_id);
        try {
            iQuestionService.deleteByQidAndPid(q_id, super.id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil(ResultCodeUtil.FAIL);
        }
        return new ResultUtil(ResultCodeUtil.SUCCESS, null);
    }

    @PostMapping("/qryByGid")
    public ResultUtil qryByGid(@RequestBody Map<String, Integer> map) {
        Integer gid = map.get("gid");
        List<GroupAndAnswer> groupAndAnswers = null;
        try {
            groupAndAnswers = iQuestionService.qryByGid(gid);
            System.out.println("groupAndAnswers" + groupAndAnswers);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil(ResultCodeUtil.FAIL);
        }
        return new ResultUtil(ResultCodeUtil.SUCCESS, groupAndAnswers);
    }

    @PostMapping("/updateQGroupRemarkById")
    public ResultUtil updateQGroupRemarkById(@RequestBody Map<String, Object> map) {
        Integer id = (Integer) map.get("id");
        String remark = (String) map.get("remark");
        try {
            iQuestionService.updateQGroupRemarkById(id, remark);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil(ResultCodeUtil.FAIL);
        }
        return new ResultUtil(ResultCodeUtil.SUCCESS, "");
    }

    @PostMapping("/qryAllQuestionInfo")
    public ResultUtil qryAllQuestionInfo() {
        List<QuestionInfo> questionInfos = null;
        try {
            questionInfos = iQuestionService.qryAllQuestionInfo();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil(ResultCodeUtil.FAIL);
        }
        return new ResultUtil(ResultCodeUtil.SUCCESS, questionInfos);
    }

    @PostMapping("/qryQuestionInfoById")
    public ResultUtil qryQuestionInfoById(@RequestBody Map<String, Integer> map) {
        Integer id = map.get("id");
        QuestionInfo questionInfo = null;
        try {
            questionInfo = iQuestionService.qryQuestionInfoById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil(ResultCodeUtil.FAIL);
        }
        return new ResultUtil(ResultCodeUtil.SUCCESS, questionInfo);
    }

    @PostMapping("/updateQuestionInfoById")
    public ResultUtil qryQuestionInfoById(@RequestBody QuestionInfo questionInfo) {
        try {
            iQuestionService.updateQuestionInfoById(questionInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil(ResultCodeUtil.FAIL);
        }
        return new ResultUtil(ResultCodeUtil.SUCCESS);
    }

    @PostMapping("/deleteQuestionInfoById")
    public ResultUtil deleteQuestionInfoById(@RequestBody Map<String, Integer> map) {
        Integer id = map.get("id");
        try {
            iQuestionService.deleteQuestionInfoById(id);
        } catch (Exception e) {
//            e.printStackTrace();
            return new ResultUtil(ResultCodeUtil.FAIL);
        }
        return new ResultUtil(ResultCodeUtil.SUCCESS);
    }

    @PostMapping("/qryQClassify")
    public ResultUtil qryQClassify() {
        List<QuestionClassify> questionClassifies = null;
        try {
            questionClassifies = iQuestionService.qryQClassify();
        } catch (Exception e) {
//            e.printStackTrace();
            return new ResultUtil(ResultCodeUtil.FAIL);
        }
        return new ResultUtil(ResultCodeUtil.SUCCESS, questionClassifies);
    }

    @PostMapping("/addQuestion")
    public ResultUtil addQuestion(@RequestBody Question question) {
        System.out.println("question" + question);
        try {
            iQuestionService.addQuestion(question);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil(ResultCodeUtil.FAIL);
        }
        return new ResultUtil(ResultCodeUtil.SUCCESS);
    }

    @PostMapping("/qryPeopleQuestionDetail")
    public ResultUtil qryPeopleQuestionDetail() {
        List<PeopleQuestionDetail> peopleQuestionDetails = null;
        try {
            peopleQuestionDetails = iQuestionService.qryPeopleQuestionDetail();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil(ResultCodeUtil.FAIL);
        }
        return new ResultUtil(ResultCodeUtil.SUCCESS, peopleQuestionDetails);
    }

}
