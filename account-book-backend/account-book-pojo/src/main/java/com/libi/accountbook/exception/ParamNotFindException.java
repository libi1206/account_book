package com.libi.accountbook.exception;

import com.libi.accountbook.exception.base.BaseException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author libi
 * 如果必要的参数找不到，就会抛出这个异常
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
