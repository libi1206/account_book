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
    List<RecordDto> selectByCondition(RecordQueryConditionDto recordQueryConditionDto);

    /**
     * 通过Id修改一条记录
     * @param RecordId
     * @param recordQueryConditionDto
     * @return
     */
    RecordDto updateById(Long RecordId, RecordQueryConditionDto recordQueryConditionDto);
}
