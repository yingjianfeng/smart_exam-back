package com.ecjtu.exam.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class PeopleQuestionDetail implements Serializable {
    int id;
    String name;
    int qid;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date create_date;
    String spentTime;
    double accuracy;
    String remark;
    String sname;
    String gname;
}
