package com.hd.biz.pojo;

public class BizReq {
    private String bizCode;
    private String bizType;
    private String uuid;
    private String idCard;
    private String addrType;

    public BizReq(String bizCode, String bizType, String uuid, String idCard, String addrType) {
        this.bizCode = bizCode;
        this.bizType = bizType;
        this.uuid = uuid;
        this.idCard = idCard;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAddrType() {
        return addrType;
    }

    public void setAddrType(String addrType) {
        this.addrType = addrType;
    }

    @Override
    public String toString() {
        return "BizReq{" +
                "bizCode='" + bizCode + '\'' +
                ", bizType='" + bizType + '\'' +
                ", uuid='" + uuid + '\'' +
                ", idCard='" + idCard + '\'' +
                ", addrType='" + addrType + '\'' +
                '}';
    }
}
