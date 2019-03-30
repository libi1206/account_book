package com.libi.accountbook.dao;

import com.libi.accountbook.entity.AccTransactionType;
import org.springframework.stereotype.Repository;

/**
 * AccTransactionTypeDAO继承基类
 */
@Repository
public interface AccTransactionTypeDAO extends MyBatisBaseDao<AccTransactionType, Long> {
}