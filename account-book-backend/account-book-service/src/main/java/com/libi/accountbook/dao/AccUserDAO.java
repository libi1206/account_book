package com.libi.accountbook.dao;

import com.libi.accountbook.entity.AccUser;
import org.springframework.stereotype.Repository;

/**
 * AccUserDAO继承基类
 */
@Repository
public interface AccUserDAO extends MyBatisBaseDao<AccUser, Long> {
}