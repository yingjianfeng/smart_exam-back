package com.ecjtu.exam.controller;

import com.ecjtu.exam.exception.CommonException;
import com.ecjtu.exam.util.JwtUtils;
import com.ecjtu.exam.util.ResultCodeUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController {

    @Autowired
    JwtUtils jwtUtils;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected int id;
    protected String name;
    protected String account;
    protected String password;
    protected String img;

    @ModelAttribute
    public void setResAnReq(HttpServletRequest request,HttpServletResponse response) throws CommonException {
        System.out.println("解析token开始");
        this.request = request;
        this.response = response;
        String token = request.getHeader("Authorization");
        System.out.println("token: "+token);
        Claims claims = jwtUtils.parseJwt(token);
        this.id = (int)claims.get("id");
        this.name = (String)claims.get("name");
        this.account = (String)claims.get("account");
        this.password = (String)claims.get("password");
        this.img = (String)claims.get("img");
        System.out.println("解析token结束");
    }

}