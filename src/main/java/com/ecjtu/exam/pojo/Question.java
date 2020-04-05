package com.ecjtu.exam.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Question implements Serializable {
    int id;
    int classify_id;
    int type;
    int grader_id;
    int school_id;
    String content;
    String option1;
    String option2;
    String option3;
    String option4;
    String answer;

}
