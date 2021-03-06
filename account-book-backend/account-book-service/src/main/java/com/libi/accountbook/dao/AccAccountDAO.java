package com.libi.accountbook.dao;

import com.libi.accountbook.entity.AccAccount;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AccAccountDAO继承基类
 */
@Repository
public interface AccAccountDAO extends MyBatisBaseDao<AccAccount, Long> {
    List<AccAccount> selectAllInUser(Long userId);
}