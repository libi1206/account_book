package com.libi.accountbook.dto;

import java.io.Serializable;

/**
 * @author libi
 * 响应模板
 */
public class ResponseDto implements Serializable {
    private int code;
    private String message;
    private Object data;
    private static final long serialVersionUID = 1L;

    public ResponseDto(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
