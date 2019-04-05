package com.libi.accountbook.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.libi.accountbook.dao.AccAccountDAO;
import com.libi.accountbook.entity.AccAccount;
import com.libi.accountbook.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author libi
 */
@Service
@Component
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccAccountDAO accAccountDAO;

    @Override
    public AccAccount insert(AccAccount accAccount, Long userId) {
        accAccount.setCreateTime(System.currentTimeMillis());
        accAccount.setUserId(userId);
        accAccountDAO.insertSelective(accAccount);
        return accAccount;
    }

    @Override
    public AccAccount update(AccAccount accAccount) {
        accAccountDAO.updateByPrimaryKeySelective(accAccount);
        return selectById(accAccount.getId());
    }

    @Override
    public AccAccount selectById(Long entityId) {
        return accAccountDAO.selectByPrimaryKey(entityId);
    }

    @Override
    public List<AccAccount> selectAll(Long userId) {
        return accAccountDAO.selectAllInUser(userId);
    }
}
