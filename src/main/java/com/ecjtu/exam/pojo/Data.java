package com.ecjtu.exam.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Data {
    int id;
    int p_id;
    String pname;
    String name;
    String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date date;
    String url;
    String type;
}
