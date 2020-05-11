package com.ecjtu.exam.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Echarts3 implements Serializable {
    String date;
    int success;
    int fail;
}
