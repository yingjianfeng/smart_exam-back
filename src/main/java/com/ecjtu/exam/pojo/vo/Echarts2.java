package com.ecjtu.exam.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Echarts2 implements Serializable {
    String name;
    int success;
    int fail;
}
