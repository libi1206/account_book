package com.libi.accountbook.service;

import com.libi.accountbook.dto.AssetsDto;
import com.libi.accountbook.entity.AccAssets;

import java.util.List;

/**
 * @author libi
 */
public interface AssetsService {
    boolean insertAssets(AssetsDto assetsDto, Long userId);

    boolean updateAssets(AssetsDto assetsDto);

    AccAssets getAssetsById(Long assetId);

    List<AccAssets> getAll(Long userId);
}
