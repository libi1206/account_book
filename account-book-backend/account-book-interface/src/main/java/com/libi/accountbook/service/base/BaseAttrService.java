package com.libi.accountbook.service.base;

import com.libi.accountbook.dto.PageDto;

import java.util.List;

/**
 * @author libi
 */
public interface BaseAttrService<Dto,Entity> {
    Entity insert(Dto dto, Long userId);

    Entity update(Dto dto);

    Entity selectById(Long entityId);

    List<Entity> selectAll(Long userId);

    PageDto selectByPage(Integer rows, Integer page, Long userId);
}
