package com.libi.accountbook.dto;

import java.io.Serializable;

/**
 * @author libi
 * 用于测试的DTO
 */
public class TestDto implements Serializable {
    private static long getSerialVersionUID = 1L;

    private String param;
    private Long currentTime;

    public TestDto(String param, Long currentTime) {
        this.param = param;
        this.currentTime = currentTime;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Long currentTime) {
        this.currentTime = currentTime;
    }
}
