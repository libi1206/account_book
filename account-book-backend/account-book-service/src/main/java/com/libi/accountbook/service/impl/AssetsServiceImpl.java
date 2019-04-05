package com.libi.accountbook.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.libi.accountbook.dao.AccAssetsDAO;
import com.libi.accountbook.dto.AssetsDto;
import com.libi.accountbook.entity.AccAssets;
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
    public AccAssets update(AssetsDto assetsDto) {
        AccAssets accAssets = new AccAssets(assetsDto);
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
}
