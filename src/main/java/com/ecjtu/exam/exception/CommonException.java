package com.ecjtu.exam.exception;

import com.ecjtu.exam.util.ResultCodeUtil;
import lombok.Getter;

@Getter
public class CommonException extends Exception {
    private ResultCodeUtil resultCode;

    public CommonException(ResultCodeUtil resultCode) {
        this.resultCode = resultCode;
    }
}
