package com.ecjtu.exam;

import com.ecjtu.exam.dao.IQuestionDao;
import com.ecjtu.exam.util.QiniuUtil;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.UUID;

@SpringBootTest
class ExamApplicationTests {

    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Autowired
    QiniuUtil qiniuUtil;
    @Test
    public void hset() {
        redisTemplate.opsForHash().put("h1","k1","v1");
    }
    @Test
    public void get() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        System.out.println(str.substring(24));
    }

    @Test
    public void qiniu() {
        try{
            String s = qiniuUtil.uploadImg("C:\\Users\\13749\\Pictures\\Saved Pictures\\980085401.jpeg");
            String url = qiniuUtil.getUrl(s);
            System.out.println(url);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Autowired
    IQuestionDao iQuestionDao;
    @Test
    public void test(){
        System.out.println(iQuestionDao.qryGradeAndClassify());
    }

}
