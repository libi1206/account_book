package com.libi.accountbook.web.api.base;

import com.libi.accountbook.dto.ResponseDto;
import com.libi.accountbook.exception.AttrNotLoginUserException;
import com.libi.accountbook.exception.ParamNotFindException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author libi
 */
public interface BaseAttrController<Dto> {
    ResponseDto create(Dto dto, HttpServletRequest request, HttpServletResponse response) throws ParamNotFindException;

    ResponseDto update(Dto dto,HttpServletRequest request, HttpServletResponse response) throws ParamNotFindException, AttrNotLoginUserException;

    ResponseDto getAll(HttpServletRequest request, HttpServletResponse response);

    ResponseDto getAllByPage(Integer rows, Integer page,HttpServletRequest request, HttpServletResponse response) throws ParamNotFindException;

    ResponseDto deleteById(Long id, HttpServletRequest request, HttpServletResponse response) throws AttrNotLoginUserException;
}
