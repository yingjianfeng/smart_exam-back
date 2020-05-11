package com.ecjtu.exam.pojo.vo;

import com.ecjtu.exam.pojo.Article;
import com.ecjtu.exam.pojo.People;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ArticleInfo extends Article implements Serializable {
    People people;
}
