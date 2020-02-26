package com.ecjtu.exam.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Grade implements Serializable {
    int id;
    String name;
}
