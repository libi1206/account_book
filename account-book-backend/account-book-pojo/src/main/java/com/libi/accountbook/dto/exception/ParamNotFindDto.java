package com.libi.accountbook.dto.exception;

import com.libi.accountbook.dto.exception.base.BaseExceptionDto;
import com.libi.accountbook.exception.ParamNotFindException;
import com.libi.accountbook.exception.base.BaseException;

import java.io.Serializable;
import java.util.List;

/**
 * @author libi
 */
public class ParamNotFindDto extends BaseExceptionDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<String> paramsName;

    public ParamNotFindDto(ParamNotFindException e) {
        super(e);
        setParamsName(e.getParamsName());
    }

    public ParamNotFindDto(String uri, Long currentTime, String paramsName) {
        super(uri, currentTime);
        this.paramsName.add(paramsName);
    }

    public List<String> getParamsName() {
        return paramsName;
    }

    public void setParamsName(List<String> paramsName) {
        this.paramsName = paramsName;
    }
}
