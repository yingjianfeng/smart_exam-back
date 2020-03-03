package com.ecjtu.exam.config.inteceptor;

import com.ecjtu.exam.exception.CommonException;
import com.ecjtu.exam.util.ResultCodeUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInteceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String autorization = request.getHeader("Authorization");
        if(!StringUtils.isEmpty(autorization) && autorization.startsWith("Bearer")){

        }
        throw new CommonException(ResultCodeUtil.UNAUTHENTICATED);
    }
}
