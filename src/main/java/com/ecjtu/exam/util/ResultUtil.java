package com.ecjtu.exam.util;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResultUtil {
    private boolean success; //是否成功
    private Integer code; //状态码
    private String message; //返回信息
    private Object data; //返回数据

    public ResultUtil(ResultCodeUtil resultCode, Object data) {
        this.success = resultCode.success;
        this.code = resultCode.code;
        this.message = resultCode.message;
        this.data = data;
    }
    public ResultUtil(ResultCodeUtil resultCode) {
        this.success = resultCode.success;
        this.code = resultCode.code;
        this.message = resultCode.message;
    }
}
