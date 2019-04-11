package com.libi.accountbook.dto.exception.base;

import com.libi.accountbook.exception.base.BaseException;

import java.io.Serializable;

/**
 * @author libi
 */
public class BaseExceptionDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String uri;
    private Long currentTime;

    public BaseExceptionDto(String uri, Long currentTime) {
        this.uri = uri;
        this.currentTime = currentTime;
    }

    public BaseExceptionDto(BaseException e) {
        if (e != null) {
            setUri(e.getUri());
            setCurrentTime(e.getCurrentTime());
        }
    }

    public BaseExceptionDto() {
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Long currentTime) {
        this.currentTime = currentTime;
    }
}
