package com.libi.accountbook.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.libi.accountbook.dao.AccAssetsDAO;
import com.libi.accountbook.dto.AssetsDto;
import com.libi.accountbook.dto.PageDto;
import com.libi.accountbook.entity.AccAccount;
import com.libi.accountbook.entity.AccAssets;
import com.libi.accountbook.exception.AttrNotLoginUserException;
import com.libi.accountbook.service.AssetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author libi
 */
@Component
@Service
public class AssetsServiceImpl implements AssetsService {
    @Autowired
    private AccAssetsDAO accAssetsDAO;

    @Override
    public AccAssets insert(AssetsDto assetsDto, Long userId) {
        AccAssets accAssets = new AccAssets(assetsDto);
        accAssets.setCreateTime(System.currentTimeMillis());
        accAssets.setUserId(userId);
        accAssetsDAO.insertSelective(accAssets);
        return accAssets;
    }

    @Override
    public AccAssets update(AssetsDto assetsDto,Long userId) throws AttrNotLoginUserException {
        AccAssets accAssets = new AccAssets(assetsDto);
        AccAssets selected = selectById(accAssets.getId());
        if (selected == null || !userId.equals(selected.getUserId())) {
            throw new AttrNotLoginUserException();
        }

        accAssetsDAO.updateByPrimaryKeySelective(accAssets);
        return selectById(assetsDto.getId());
    }

    @Override
    public AccAssets selectById(Long assetId) {
        return accAssetsDAO.selectByPrimaryKey(assetId);
    }

    @Override
    public List<AccAssets> selectAll(Long userId) {
        return accAssetsDAO.selectAllInUser(userId);
    }

    @Override
    public PageDto selectByPage(Integer rows, Integer page, Long userId) {
        PageHelper.startPage(page, rows);
        List<AccAssets> accAssets = accAssetsDAO.selectAllInUser(userId);
        PageInfo<AccAssets> pageInfo = new PageInfo<>(accAssets);
        return new PageDto(pageInfo.getPageSize(),pageInfo.getPageNum(),pageInfo.getPages(),accAssets);
    }

    @Override
    public AccAssets deleteById(Long id, Long userId) throws AttrNotLoginUserException {
        AccAssets assets = selectById(id);
        if (assets == null || !assets.getUserId().equals(userId)) {
            throw new AttrNotLoginUserException();
        }
        accAssetsDAO.deleteByPrimaryKey(id);
        return assets;
    }
}
