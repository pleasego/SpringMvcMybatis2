package com.xtkj.tools;

public class GsonResult<T> {

    private T obj;
    private String msg;
    private Integer stateCode;

    public GsonResult() {
    }

    public GsonResult(T obj, String msg, Integer stateCode) {
        this.obj = obj;
        this.msg = msg;
        this.stateCode = stateCode;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getStateCode() {
        return stateCode;
    }

    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }
}
