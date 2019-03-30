package com.libi.accountbook.dao;

import com.libi.accountbook.entity.AccTreasury;
import org.springframework.stereotype.Repository;

/**
 * AccTreasuryDAO继承基类
 */
@Repository
public interface AccTreasuryDAO extends MyBatisBaseDao<AccTreasury, Long> {
}