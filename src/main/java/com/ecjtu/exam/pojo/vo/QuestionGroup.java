package com.ecjtu.exam.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class QuestionGroup implements Serializable {
    int classify;
    int count;
    int success;
    int fail;
}
