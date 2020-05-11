package com.ecjtu.exam.service.impl;

import com.ecjtu.exam.dao.IArticleDao;
import com.ecjtu.exam.pojo.Article;
import com.ecjtu.exam.pojo.vo.ArticleInfo;
import com.ecjtu.exam.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements IArticleService {
    @Autowired
    IArticleDao iArticleDao;

    @Override
    public void add(Article article) throws Exception {
        iArticleDao.add(article);
    }

    @Override
    public ArticleInfo qryById(int id) throws Exception {
        return iArticleDao.qryById(id);
    }

    @Override
    public List<ArticleInfo> qryAll() throws Exception {
        return iArticleDao.qryAll();
    }

    @Override
    public void updateVisitsById(int id, int visits) {
        iArticleDao.updateVisitsById(id,visits);
    }
}
