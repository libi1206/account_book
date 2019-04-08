package com.libi.accountbook.web.api.base;

import com.libi.accountbook.dto.ResponseDto;
import com.libi.accountbook.exception.AttrNotLoginUserException;
import com.libi.accountbook.exception.ParamNotFindException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author libi
 */
public interface BaseAttrController<Dto> {
    ResponseDto create(Dto dto, HttpServletRequest request) throws ParamNotFindException;

    ResponseDto update(Dto dto,HttpServletRequest request) throws ParamNotFindException, AttrNotLoginUserException;

    ResponseDto getAll();

    ResponseDto getAllByPage(Integer rows, Integer page,HttpServletRequest request) throws ParamNotFindException;
}
