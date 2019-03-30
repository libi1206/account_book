package com.libi.accountbook.dao;

import com.libi.accountbook.entity.AccAssets;
import org.springframework.stereotype.Repository;

/**
 * AccAssetsDAO继承基类
 */
@Repository
public interface AccAssetsDAO extends MyBatisBaseDao<AccAssets, Long> {
}