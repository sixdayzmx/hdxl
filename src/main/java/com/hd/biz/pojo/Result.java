package com.hd.biz.pojo;

public class Result {

    private boolean flag;//是否成功
    private String message;//返回信息
    private Object data;//返回数据

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Result(boolean flag, String message, Object data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }

    public Result(boolean flag, Object data) {
        this.flag = flag;
        this.data = data;
    }

    public Result(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public Result(boolean flag, String message) {
        this.flag = flag;
        this.message = message;
    }

    public Result() {
    }
}
