package com.ecjtu.exam.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
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

    public Data( int p_id, String name, String description, Date date, String type) {
        this.p_id = p_id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.type = type;
    }
}
