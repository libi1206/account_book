package com.libi.accountbook.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.libi.accountbook.dao.AccTreasuryDAO;
import com.libi.accountbook.entity.AccTreasury;
import com.libi.accountbook.service.TreasuryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author libi
 */
@Service
@Component
public class TreasuryServiceImpl implements TreasuryService {
    @Autowired
    private AccTreasuryDAO accTreasuryDAO;

    @Override
    public AccTreasury insert(AccTreasury accTreasury, Long userId) {
        accTreasury.setUserId(userId);
        accTreasury.setCreateTime(System.currentTimeMillis());
        accTreasuryDAO.insertSelective(accTreasury);
        return accTreasury;
    }

    @Override
    public AccTreasury update(AccTreasury accTreasury) {
        accTreasuryDAO.updateByPrimaryKeySelective(accTreasury);
        return selectById(accTreasury.getId());
    }

    @Override
    public AccTreasury selectById(Long entityId) {
        return accTreasuryDAO.selectByPrimaryKey(entityId);
    }

    @Override
    public List<AccTreasury> selectAll(Long userId) {
        return accTreasuryDAO.selectAllByUser(userId);
    }

}
