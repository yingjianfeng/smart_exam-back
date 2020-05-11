package com.ecjtu.exam.service;

import com.ecjtu.exam.pojo.Article;
import com.ecjtu.exam.pojo.vo.ArticleInfo;

import java.util.List;

public interface IArticleService {
    void add(Article article) throws Exception;

    ArticleInfo qryById(int id) throws Exception;

    List<ArticleInfo> qryAll() throws Exception;

    void updateVisitsById(int id, int visits);
}
