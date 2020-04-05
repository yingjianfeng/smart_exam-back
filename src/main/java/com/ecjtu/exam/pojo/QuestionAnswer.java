package com.ecjtu.exam.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class QuestionAnswer implements Serializable {
    int id;
    int q_id;
    String answer;
    int success;
    int g_id;

    public QuestionAnswer(int q_id, String answer, int success, int g_id) {
        this.q_id = q_id;
        this.answer = answer;
        this.success = success;
        this.g_id = g_id;
    }
}
