package com.libi.accountbook.dao;

import com.libi.accountbook.entity.FamilyMappingKey;
import org.springframework.stereotype.Repository;

/**
 * FamilyMappingDAO继承基类
 */
@Repository
public interface FamilyMappingDAO extends MyBatisBaseDao<FamilyMappingKey,Long> {
    int deleteByPrimaryKey(FamilyMappingKey key);
}