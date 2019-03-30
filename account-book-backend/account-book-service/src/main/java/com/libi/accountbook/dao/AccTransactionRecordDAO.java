package com.libi.accountbook.dao;

import com.libi.accountbook.entity.AccTransactionRecord;
import org.springframework.stereotype.Repository;

/**
 * AccTransactionRecordDAO继承基类
 */
@Repository
public interface AccTransactionRecordDAO extends MyBatisBaseDao<AccTransactionRecord, Long> {
}