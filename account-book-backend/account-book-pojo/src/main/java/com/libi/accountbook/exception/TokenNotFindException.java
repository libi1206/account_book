package com.libi.accountbook.exception;

import com.libi.accountbook.exception.base.BaseException;

/**
 * @author libi
 */
public class TokenNotFindException extends BaseException {
    private String Message;

    public TokenNotFindException(String uri, Long currentTime, String message) {
        super(uri, currentTime);
        Message = message;
    }

    public TokenNotFindException() {
    }

    @Override
    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
