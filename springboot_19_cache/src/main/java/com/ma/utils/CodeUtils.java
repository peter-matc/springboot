package com.ma.utils;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CodeUtils {

    private String [] patch = {"00000","0000","000","00","0",""};

    public String generator(String tele){



        int hash = tele.hashCode();
        int encryption = 20206666;
        long result = hash ^ encryption;

        long nowTime = System.currentTimeMillis();

        result = result ^ nowTime;

        long code  = result % 1000000;
        code = code < 0? -code : code;

        String codeStr = code + "";
        int len = codeStr.length();

        return patch[len-1]+codeStr;

    }
    // 如果有就返回 如果没有就返回一个null
    @Cacheable(key = "#tele", value = "smsCode")
    public String get(String tele){
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new CodeUtils().generator("18888888888"));
    }
}
