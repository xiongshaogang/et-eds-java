package com.edaisong.entity.common;

public class OpenResponseBase {
    private int responseCode = ResponseCode.SUCESS;
    private String message;
    private String processServerIp;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProcessServerIp() {
        return processServerIp;
    }

    public void setProcessServerIp(String processServerIp) {
        this.processServerIp = processServerIp;
    }
}