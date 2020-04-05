package com.ecjtu.exam.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class QuestionAnswerGroup implements Serializable {
    int id;
    int p_id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date create_date;

    public QuestionAnswerGroup(int p_id, Date create_date) {
        this.p_id = p_id;
        this.create_date = create_date;
    }
}
