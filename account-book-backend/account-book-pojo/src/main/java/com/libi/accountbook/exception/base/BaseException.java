package com.libi.accountbook.exception.base;

/**
 * @author libi
 */
public class BaseException extends Exception {
    private String uri;
    private Long currentTime;

    public BaseException(String uri, Long currentTime) {
        this.uri = uri;
        this.currentTime = currentTime;
    }

    public BaseException() {
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
