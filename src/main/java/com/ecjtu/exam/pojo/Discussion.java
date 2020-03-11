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
public class Discussion implements Serializable {
    int id;
    int people_id;
    String people_name;
    String content;
    String imgs;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date create_time;
    int state;
    int like;
    int comment;
    int share;
    int parent_id;
}
