package ru.alfastrah.smskafka.model;

import java.io.Serializable;

public class BeforeSmsSend implements Serializable {
    private String gateway;
    private String login;
    private String phoneNumber;
    private String message;
    private String password;
    private String esbId;

    public BeforeSmsSend(String gateway, String login, String phoneNumber, String message, String esbId, String password) {
        this.gateway = gateway;
        this.login = login;
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.esbId = esbId;
        this.password = password;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEsbId() {
        return esbId;
    }

    public void setEsbId(String esbId) {
        this.esbId = esbId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
