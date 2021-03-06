package com.libi.accountbook.dao;

import com.libi.accountbook.entity.AccTransactionType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AccTransactionTypeDAO继承基类
 */
@Repository
public interface AccTransactionTypeDAO extends MyBatisBaseDao<AccTransactionType, Long> {
    List<AccTransactionType> selectAllByUser(Long userId);

    /**
     * 把父节点置空
     * @param id
     * @return
     */
    int setParentIdNull(Long id);
}