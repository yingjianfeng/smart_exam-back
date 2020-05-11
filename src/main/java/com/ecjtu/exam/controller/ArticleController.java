package com.ecjtu.exam.controller;

import com.ecjtu.exam.pojo.Article;
import com.ecjtu.exam.pojo.vo.ArticleInfo;
import com.ecjtu.exam.service.IArticleService;
import com.ecjtu.exam.util.ResultCodeUtil;
import com.ecjtu.exam.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController {
    @Autowired
    IArticleService iArticleService;

    @PostMapping("/addArticle")
    public ResultUtil addArticle(@RequestBody Article article) {
        System.out.println("article: " + article);
        article.setP_id(super.id);
        article.setCreate_date(new Date());
        article.setVisits(0);
        try {
            iArticleService.add(article);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil(ResultCodeUtil.FAIL);
        }
        return new ResultUtil(ResultCodeUtil.SUCCESS);
    }

    @GetMapping("/qryArticleById")
    public ResultUtil qryArticleById(int id) {
        System.out.println("id: " + id);
        ArticleInfo articleInfo = null;
        try {
            articleInfo = iArticleService.qryById(id);
            iArticleService.updateVisitsById(id, articleInfo.getVisits() + 1);
            articleInfo.setVisits(articleInfo.getVisits() + 1);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil(ResultCodeUtil.FAIL);
        }
        return new ResultUtil(ResultCodeUtil.SUCCESS, articleInfo);
    }

    @GetMapping("/qryAll")
    public ResultUtil qryAll() {
        List<ArticleInfo> articleInfos = null;
        try {
            articleInfos = iArticleService.qryAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil(ResultCodeUtil.FAIL);
        }
        return new ResultUtil(ResultCodeUtil.SUCCESS, articleInfos);
    }

}
