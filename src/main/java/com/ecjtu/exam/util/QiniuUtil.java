package com.ecjtu.exam.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;

@Component
@ConfigurationProperties("qiniuyun.config")
public class QiniuUtil {
    String accessKey;
    String secretKey;
    String bucket;
    String baseUrl;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String uploadImg(String url) throws Exception {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.huadong());
//...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = System.currentTimeMillis() + "";
        ;
        Auth auth = Auth.create(this.accessKey, this.secretKey);
        String upToken = auth.uploadToken(this.bucket);
        try {
            Response response = uploadManager.put(url, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//            System.out.println(putRet.key);
            return putRet.key;
//            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
//            Response r = ex.response;
//            System.err.println(r.toString());
            throw ex;
        }
    }

    public String getUrl(String url) {
        return this.baseUrl + url;
    }

    public String uploadByStream(FileInputStream file) throws Exception {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        String key = System.currentTimeMillis() + "";
        try {
            Response response = uploadManager.put(file, key, upToken,null,null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//            System.out.println(putRet.key);
//            System.out.println(putRet.hash);
            return putRet.key;
        } catch (QiniuException ex) {
//            Response r = ex.response;
//            System.err.println(r.toString());
            throw ex;
        }
    }

    public void delete(String key)throws Exception{
        Configuration cfg = new Configuration(Region.region0());
        Auth auth = Auth.create(this.accessKey, this.secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(this.bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }

}
