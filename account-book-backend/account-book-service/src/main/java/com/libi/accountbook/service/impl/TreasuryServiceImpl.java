package com.libi.accountbook.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.libi.accountbook.dao.AccTreasuryDAO;
import com.libi.accountbook.dto.PageDto;
import com.libi.accountbook.entity.AccAssets;
import com.libi.accountbook.entity.AccTreasury;
import com.libi.accountbook.exception.AttrNotLoginUserException;
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
    public AccTreasury update(AccTreasury accTreasury,Long userId) throws AttrNotLoginUserException {
        AccTreasury selected = selectById(accTreasury.getId());
        if (selected == null || !userId.equals(selected.getUserId())) {
            throw new AttrNotLoginUserException();
        }
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

    @Override
    public PageDto selectByPage(Integer rows, Integer page, Long userId) {
        PageHelper.startPage(page, rows);
        List<AccTreasury> accTreasuries = accTreasuryDAO.selectAllByUser(userId);
        PageInfo<AccTreasury> pageInfo = new PageInfo<>(accTreasuries);
        return new PageDto(pageInfo.getPageSize(),pageInfo.getPageNum(),pageInfo.getPages(),accTreasuries);
    }

    @Override
    public AccTreasury deleteById(Long id, Long userId) throws AttrNotLoginUserException {
        AccTreasury accTreasury = selectById(id);
        if (accTreasury == null || !accTreasury.getUserId().equals(userId)) {
            throw new AttrNotLoginUserException();
        }
        accTreasuryDAO.deleteByPrimaryKey(id);
        return accTreasury;
    }
}
