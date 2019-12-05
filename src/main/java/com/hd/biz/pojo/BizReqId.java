package com.hd.biz.pojo;

public class BizReqId {
    private String bizCode;
    private String bizType;
    private String id;
    private String addrType;

    public BizReqId(String bizCode, String bizType, String id, String addrType) {
        this.bizCode = bizCode;
        this.bizType = bizType;
        this.id = id;
        this.addrType = addrType;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getAddrType() {
        return addrType;
    }

    public void setAddrType(String addrType) {
        this.addrType = addrType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BizReqId{" +
                "bizCode='" + bizCode + '\'' +
                ", bizType='" + bizType + '\'' +
                ", id='" + id + '\'' +
                ", addrType='" + addrType + '\'' +
                '}';
    }
}
