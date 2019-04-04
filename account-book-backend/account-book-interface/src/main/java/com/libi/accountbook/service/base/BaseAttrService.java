package com.libi.accountbook.service.base;

import java.util.List;

/**
 * @author libi
 */
public interface BaseAttrService<Dto,Entity> {
    Entity insert(Dto dto, Long userId);

    Entity update(Dto dto);

    Entity selectById(Long entityId);

    List<Entity> selectAll(Long userId);
}
