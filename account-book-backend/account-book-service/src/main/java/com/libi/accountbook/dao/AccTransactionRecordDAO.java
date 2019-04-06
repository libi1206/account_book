package com.libi.accountbook.dao;

import com.libi.accountbook.dto.RecordQueryConditionDto;
import com.libi.accountbook.entity.AccTransactionRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AccTransactionRecordDAO继承基类
 */
@Repository
public interface AccTransactionRecordDAO extends MyBatisBaseDao<AccTransactionRecord, Long> {
    /**
     * 查到这个家庭所有的交易记录
     * @param familyId
     * @return
     */
    List<AccTransactionRecord> selectRecordByFamily(Long familyId);

    /**
     * 查出其他条件的交易记录
     * @param recordQueryConditionDto
     * @return
     */
    List<AccTransactionRecord> selectByCondition(RecordQueryConditionDto recordQueryConditionDto);
}