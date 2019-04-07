package com.libi.accountbook.exception;

import com.libi.accountbook.exception.base.BaseException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author libi
 */
public class ParamNotFindException extends BaseException {
    private List<String> paramsName;

    public ParamNotFindException() {
    }

    public ParamNotFindException(String uri, Long currentTime, String message) {
        super(uri, currentTime, message);
    }

    public List<String> getParamsName() {
        return paramsName;
    }

    public void setParamsName(List<String>  paramsName) {
        this.paramsName = paramsName;
    }
}
