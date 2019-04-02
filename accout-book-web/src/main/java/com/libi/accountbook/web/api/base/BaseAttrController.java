package com.libi.accountbook.web.api.base;

import com.libi.accountbook.dto.ResponseDto;

/**
 * @author libi
 */
public interface BaseAttrController<Dto> {
    ResponseDto create(Dto dto);

    ResponseDto update(Dto dto);

    ResponseDto getAll();

    ResponseDto delete(Long id);
}
