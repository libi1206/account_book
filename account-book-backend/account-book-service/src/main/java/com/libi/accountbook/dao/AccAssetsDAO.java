package com.libi.accountbook.dao;

import com.libi.accountbook.entity.AccAssets;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AccAssetsDAO继承基类
 */
@Repository
public interface AccAssetsDAO extends MyBatisBaseDao<AccAssets, Long> {
    List<AccAssets> selectAllInUser(Long userId);
}