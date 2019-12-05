package com.hd.biz.pojo;

public class Biz {
    private int id;
    private String bizCode;
    private String callBackAddr;
    private String bizType;

    public Biz() {
    }

    public Biz(int id, String bizCode, String callBackAddr, String bizType) {
        this.id = id;
        this.bizCode = bizCode;
        this.callBackAddr = callBackAddr;
        this.bizType = bizType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getcallBackAddr() {
        return callBackAddr;
    }

    public void setcallBackAddr(String callBackAddr) {
        this.callBackAddr = callBackAddr;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    @Override
    public String toString() {
        return "Biz{" +
                "id=" + id +
                ", bizCode=" + bizCode +
                ", callBackAddr='" + callBackAddr + '\'' +
                ", bizType='" + bizType + '\'' +
                '}';
    }
}
