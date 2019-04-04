package com.libi.accountbook.dao;

import com.libi.accountbook.entity.AccUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AccUserDAO继承基类
 */
@Repository
public interface AccUserDAO extends MyBatisBaseDao<AccUser, Long> {
    public AccUser selectUserByUserName(String userName);

    public AccUser selectUserByPhone(String phone);

    List<AccUser> selectAllUserByFamily(Long familyId);
}