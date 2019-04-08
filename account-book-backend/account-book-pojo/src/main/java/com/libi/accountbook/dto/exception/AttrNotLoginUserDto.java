package com.libi.accountbook.dto.exception;

import com.libi.accountbook.dto.exception.base.BaseExceptionDto;
import com.libi.accountbook.exception.AttrNotLoginUserException;
import com.libi.accountbook.exception.base.BaseException;

import java.io.Serializable;

/**
 * @author libi
 */
public class AttrNotLoginUserDto extends BaseExceptionDto implements Serializable {
    private static final long serialVersionUID = 1L;


    public AttrNotLoginUserDto(AttrNotLoginUserException e) {
        super(e);
    }
}
