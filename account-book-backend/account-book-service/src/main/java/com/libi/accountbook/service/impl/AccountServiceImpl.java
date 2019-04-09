package com.libi.accountbook.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.libi.accountbook.dao.AccAccountDAO;
import com.libi.accountbook.dto.PageDto;
import com.libi.accountbook.entity.AccAccount;
import com.libi.accountbook.exception.AttrNotLoginUserException;
import com.libi.accountbook.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)
    public AccAccount update(AccAccount accAccount, Long userId) throws AttrNotLoginUserException {
        AccAccount selected = selectById(accAccount.getId());
        if (selected == null || !userId.equals(selected.getUserId())) {
            throw new AttrNotLoginUserException();
        }

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

    @Override
    public PageDto selectByPage(Integer rows, Integer page, Long userId) {
        PageHelper.startPage(page, rows);
        List<AccAccount> accAccounts = accAccountDAO.selectAllInUser(userId);
        PageInfo<AccAccount> pageInfo = new PageInfo<>(accAccounts);
        return new PageDto(pageInfo.getPageSize(),pageInfo.getPageNum(),pageInfo.getPages(),accAccounts);
    }

    @Override
    public AccAccount deleteById(Long id, Long userId) throws AttrNotLoginUserException {
        AccAccount accAccount = selectById(id);
        if (accAccount == null || !accAccount.getUserId().equals(userId)) {
            throw new AttrNotLoginUserException();
        }
        accAccountDAO.deleteByPrimaryKey(id);
        return accAccount;
    }

}
