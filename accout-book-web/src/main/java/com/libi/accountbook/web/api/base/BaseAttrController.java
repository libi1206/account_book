package com.libi.accountbook.web.api.base;

import com.libi.accountbook.dto.ResponseDto;

import javax.servlet.http.HttpServletRequest;

/**
 * @author libi
 */
public interface BaseAttrController<Dto> {
    ResponseDto create(Dto dto, HttpServletRequest request);

    ResponseDto update(Dto dto,HttpServletRequest request);

    ResponseDto getAll();
}
