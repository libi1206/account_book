package com.libi.accountbook.dao;

import com.libi.accountbook.entity.AccFamily;
import org.springframework.stereotype.Repository;

/**
 * AccFamilyDAO继承基类
 */
@Repository
public interface AccFamilyDAO extends MyBatisBaseDao<AccFamily, Long> {
}