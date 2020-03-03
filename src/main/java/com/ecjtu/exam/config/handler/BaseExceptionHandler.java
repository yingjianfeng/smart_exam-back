package com.ecjtu.exam.config.handler;

import com.ecjtu.exam.exception.CommonException;
import com.ecjtu.exam.util.ResultCodeUtil;
import com.ecjtu.exam.util.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@ControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultUtil error(HttpServletRequest request, HttpServletResponse response, Exception e) {
        e.printStackTrace();
        if(e.getClass() == CommonException.class) {
            //类型转型
            CommonException ce = (CommonException) e;
            ResultUtil result = new ResultUtil(ce.getResultCode());
            return result;
        }else{
            ResultUtil result = new ResultUtil(ResultCodeUtil.SERVER_ERROR);
            return result;
        }
    }
}
