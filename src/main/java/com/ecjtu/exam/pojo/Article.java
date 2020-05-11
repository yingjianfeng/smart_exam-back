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
@NoArgsConstructor
@ToString
public class Article implements Serializable {
    int id;
    String name;
    String content;
    int p_id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date create_date;
    int visits;
    String introduce;
}
