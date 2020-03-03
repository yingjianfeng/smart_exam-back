package com.ecjtu.exam.util;

import java.util.UUID;

public class CommonUtil {
    public static String getRandomStr() {
        return UUID.randomUUID().toString().substring(24);
    }
}
