package com.ssm.demo.util;

/**
 * 定义httpMethod枚举
 */
public enum HttpMethod {

    /**
     * post方法
     */
    POST("POST"),
    /**
     *get方法
     */
    GET("GET");

    private String value;

    private HttpMethod(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
