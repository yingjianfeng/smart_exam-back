package com.ecjtu.exam.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class People implements Serializable {
    int id;
    String name;
    String account;
    String password;
    String phone;
    int grade_id;
    int school_id;
    int status;
    int type;
}
