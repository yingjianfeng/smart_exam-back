package com.ecjtu.exam.config.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Order(1)
//@Component
public class CorsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest)req;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Content-Length, Authorization, Accept, X-Requested-With ");
        System.out.println("*********************************过滤器被使用**************************");
        if("OPTIONS".equals(request.getMethod())){
            response.setStatus(HttpStatus.NO_CONTENT.value());
            System.out.println("OPTIONS处理，直接返回");
            return;
        }
        chain.doFilter(req, res);
    }
}