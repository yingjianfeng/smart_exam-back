package com.ecjtu.exam.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QuestionInfo {
    int id;
    int type;
    int f_id;
    String content;
    String option1;
    String option2;
    String option3;
    String option4;
    String answer;
    String grade;
    String classify;
}
