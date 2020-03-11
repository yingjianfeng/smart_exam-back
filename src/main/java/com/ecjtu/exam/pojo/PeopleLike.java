package com.ecjtu.exam.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class PeopleLike implements Serializable {
    int id;
    int people_id;
    int discussion_id;
}
