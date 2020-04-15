package com.ecjtu.exam.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class GroupAndAnswer implements Serializable {
    String content;
    int type;
    String option1;
    String option2;
    String option3;
    String option4;
    String answer;
    String result;
    int success;
}
