package com.zoopark.lib.common;

public enum RespStatus {

    SUCCESS("0");

    private String code;

    RespStatus(String code) {
        this.code = code;
    }

    public String getValue() {
        return code;
    }

}