package com.libi.accountbook.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.libi.accountbook.dao.AccTransactionTypeDAO;
import com.libi.accountbook.dto.PageDto;
import com.libi.accountbook.dto.TransactionTypeDto;
import com.libi.accountbook.entity.AccAccount;
import com.libi.accountbook.entity.AccAssets;
import com.libi.accountbook.entity.AccTransactionType;
import com.libi.accountbook.exception.AttrNotLoginUserException;
import com.libi.accountbook.service.TransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author libi
 */
@Service
@Component
public class TransactionTypeServiceImpl implements TransactionTypeService {
    @Autowired
    private AccTransactionTypeDAO transactionTypeDAO;

    @Override
    public AccTransactionType insert(TransactionTypeDto transactionTypeDto, Long userId) {
        AccTransactionType transactionType = new AccTransactionType(transactionTypeDto);
        transactionType.setCreateTime(System.currentTimeMillis());
        transactionType.setUserId(userId);
        transactionTypeDAO.insertSelective(transactionType);
        return transactionType;
    }

    @Override
    public AccTransactionType update(TransactionTypeDto transactionTypeDto,Long userId) throws AttrNotLoginUserException {
        AccTransactionType selected = selectById(transactionTypeDto.getId());
        if (selected == null || !userId.equals(selected.getUserId())) {
            throw new AttrNotLoginUserException();
        }
        //如果传入的父节点Id是-1，就把父节点置空以达到把这个分类变成父分类的目的
        if (transactionTypeDto.getParentId().equals(-1L)) {
            transactionTypeDAO.setParentIdNull(transactionTypeDto.getId());
            transactionTypeDto.setParentId(null);
        }
        transactionTypeDAO.updateByPrimaryKeySelective(new AccTransactionType(transactionTypeDto));
        return selectById(transactionTypeDto.getId());
    }

    @Override
    public AccTransactionType selectById(Long entityId) {
        return transactionTypeDAO.selectByPrimaryKey(entityId);
    }

    @Override
    public List<AccTransactionType> selectAll(Long userId) {
        return transactionTypeDAO.selectAllByUser(userId);
    }

    @Override
    public PageDto selectByPage(Integer rows, Integer page, Long userId) {
        PageHelper.startPage(page, rows);
        List<AccTransactionType> accTransactionTypes = transactionTypeDAO.selectAllByUser(userId);
        PageInfo<AccTransactionType> pageInfo = new PageInfo<>(accTransactionTypes);
        return new PageDto(pageInfo.getPageSize(),pageInfo.getPageNum(),pageInfo.getPages(),accTransactionTypes);
    }

    @Override
    public AccTransactionType deleteById(Long id, Long userId) throws AttrNotLoginUserException {
        AccTransactionType accTransactionType = selectById(id);
        if (accTransactionType == null || !accTransactionType.getUserId().equals(userId)) {
            throw new AttrNotLoginUserException();
        }
        transactionTypeDAO.deleteByPrimaryKey(id);
        return accTransactionType;
    }
}
