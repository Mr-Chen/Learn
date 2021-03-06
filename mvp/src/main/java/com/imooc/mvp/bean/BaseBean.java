package com.imooc.mvp.bean;

import java.io.Serializable;

/**
 * Author   ： cxw
 * Date     ： 2022/4/23 00:19
 * Explain  :  请在此输入文件说明
 */

public class BaseBean<T> implements Serializable {

    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
