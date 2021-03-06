package com.ecjtu.exam.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class Grade implements Serializable {
    int id;
    String name;
    List<QuestionClassify> questionClassify;
}
