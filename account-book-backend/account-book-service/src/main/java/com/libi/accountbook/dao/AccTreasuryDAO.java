package com.libi.accountbook.dao;

import com.libi.accountbook.entity.AccTreasury;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AccTreasuryDAO继承基类
 */
@Repository
public interface AccTreasuryDAO extends MyBatisBaseDao<AccTreasury, Long> {
    List<AccTreasury> selectAllByUser(Long userId);
}