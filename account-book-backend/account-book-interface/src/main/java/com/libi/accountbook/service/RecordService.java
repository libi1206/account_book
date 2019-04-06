package com.libi.accountbook.service;

import com.libi.accountbook.dto.RecordDto;
import com.libi.accountbook.dto.RecordQueryConditionDto;
import com.libi.accountbook.entity.AccTransactionRecord;

import java.util.List;

/**
 * @author libi
 */
public interface RecordService {
    /**
     * 插入一条记录
     *
     * @param record
     * @return
     */
    RecordDto insertRecord(AccTransactionRecord record, Long userId);

    /**
     * 只查询一条记录
     * @param recordId
     * @return
     */
    RecordDto selectById(Long recordId);

    /**
     * 条件查询
     * @param recordQueryConditionDto
     * @return
     */
    List<RecordDto> selectByCondition(RecordQueryConditionDto recordQueryConditionDto,Long userId);

    /**
     * 查询当前用户的所有记录
     * @param userId
     * @return
     */
    List<RecordDto> selectByUser(Long userId);
    /**
     * 通过Id修改一条记录
     * @return
     */
    RecordDto updateById(Long recordId, String note ,Long typeId);
}
