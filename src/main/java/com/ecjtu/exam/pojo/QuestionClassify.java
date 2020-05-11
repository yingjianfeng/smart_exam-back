package com.ecjtu.exam.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class QuestionClassify implements Serializable {
    int id;
    String name;
    String img;
    List<Grade> grade;
}
