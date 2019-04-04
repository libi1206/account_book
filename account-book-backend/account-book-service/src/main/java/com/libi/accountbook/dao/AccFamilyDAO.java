package com.libi.accountbook.dao;

import com.libi.accountbook.entity.AccFamily;
import com.libi.accountbook.entity.AccUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AccFamilyDAO继承基类
 */
@Repository
public interface AccFamilyDAO extends MyBatisBaseDao<AccFamily, Long> {
    List<AccFamily> selectAllFamilyByUser(Long userId);
}