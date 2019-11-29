package ru.alfastrah.smskafka.model;

import java.io.Serializable;

public class AfterSmsSend implements Serializable {
    private String esbId;
    private String status;
    private String phone;
    private String gatewayAnswer;
    private String time;
    private String smsid;
    private String smscld;

    public String getEsbId() {
        return esbId;
    }

    public void setEsbId(String esbId) {
        this.esbId = esbId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGatewayAnswer() {
        return gatewayAnswer;
    }

    public void setGatewayAnswer(String gatewayAnswer) {
        this.gatewayAnswer = gatewayAnswer;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSmsid() {
        return smsid;
    }

    public void setSmsid(String smsid) {
        this.smsid = smsid;
    }

    public String getSmscld() {
        return smscld;
    }

    public void setSmscld(String smscld) {
        this.smscld = smscld;
    }
}
