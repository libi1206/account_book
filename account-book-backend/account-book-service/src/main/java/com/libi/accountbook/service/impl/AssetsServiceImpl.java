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
    public boolean insertAssets(AssetsDto assetsDto, Long userId) {
        AccAssets accAssets = new AccAssets(assetsDto);
        accAssets.setCreateTime(System.currentTimeMillis());
        accAssets.setUserId(userId);
        return accAssetsDAO.insertSelective(accAssets) == 1;
    }

    @Override
    public boolean updateAssets(AssetsDto assetsDto) {
        AccAssets accAssets = new AccAssets(assetsDto);
        return accAssetsDAO.updateByPrimaryKeySelective(accAssets) == 1;
    }

    @Override
    public AccAssets getAssetsById(Long assetId) {
        return accAssetsDAO.selectByPrimaryKey(assetId);
    }

    @Override
    public List<AccAssets> getAll(Long userId) {
        return accAssetsDAO.selectAllInUser(userId);
    }
}
