package com.ecjtu.exam.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Role {
    int id;
    String name;
    String description;
    List<Resource> resources;
}
