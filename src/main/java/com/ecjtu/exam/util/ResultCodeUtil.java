package com.ecjtu.exam.util;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ResultCodeUtil {
    SUCCESS(true, 1000, "操作成功"),
    FAIL(false, 1001, "操作失败"),
    UNAUTHENTICATED(false, 1001, "未登录"),
    UNAUTHORISS(false, 1001, "权限不足"),
    //---用户操作返回码  2xxxx----
    USENAMEORPASSWORDERROR(false,20001,"用户名或密码错误"),
    SERVER_ERROR(false,99999,"抱歉，系统繁忙，请稍后重试！");
    boolean success;
    int code;
    String message;

}
