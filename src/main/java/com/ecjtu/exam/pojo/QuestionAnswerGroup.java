package com.ecjtu.exam.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class QuestionAnswerGroup implements Serializable {
    int id;
    int p_id;
    String p_name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date create_date;
    int times;
    String spentTime;
    double accuracy;
    String remark;

    public QuestionAnswerGroup(int p_id, Date create_date) {
        this.p_id = p_id;
        this.create_date = create_date;
    }

    public QuestionAnswerGroup(int p_id, Date create_date, String spentTime) {
        this.p_id = p_id;
        this.create_date = create_date;
        this.spentTime = spentTime;
    }

    public QuestionAnswerGroup(int p_id, Date create_date, String spentTime, double accuracy) {
        this.p_id = p_id;
        this.create_date = create_date;
        this.spentTime = spentTime;
        this.accuracy = accuracy;
    }
}
