package com.ecjtu.exam.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class Role implements Serializable {
    int id;
    String name;
    String description;
    List<Resource> resources;
}
