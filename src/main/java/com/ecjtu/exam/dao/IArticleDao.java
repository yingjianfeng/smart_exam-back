package com.ecjtu.exam.dao;

import com.ecjtu.exam.pojo.Article;
import com.ecjtu.exam.pojo.vo.ArticleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IArticleDao {
    void add(Article article);

    ArticleInfo qryById(int id);

    List<ArticleInfo> qryAll();

    void updateVisitsById(@Param("id") int id,@Param("visits") int visits);
}
