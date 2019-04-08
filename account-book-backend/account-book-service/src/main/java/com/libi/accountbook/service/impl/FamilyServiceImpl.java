package com.libi.accountbook.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.libi.accountbook.dao.AccFamilyDAO;
import com.libi.accountbook.dao.AccUserDAO;
import com.libi.accountbook.dao.FamilyMappingDAO;
import com.libi.accountbook.dto.FamilyDto;
import com.libi.accountbook.dto.PageDto;
import com.libi.accountbook.dto.UserDto;
import com.libi.accountbook.entity.AccAssets;
import com.libi.accountbook.entity.AccFamily;
import com.libi.accountbook.entity.AccUser;
import com.libi.accountbook.entity.FamilyMappingKey;
import com.libi.accountbook.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author libi
 */
@Service
@Component
public class FamilyServiceImpl implements FamilyService {
    @Autowired
    private AccFamilyDAO accFamilyDAO;
    @Autowired
    private AccUserDAO accUserDAO;
    @Autowired
    private FamilyMappingDAO familyMappingDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AccFamily insert(FamilyDto familyDto, Long id) {
        AccFamily family = new AccFamily(familyDto);
        family.setCreateTime(System.currentTimeMillis());
        //创建家庭
        accFamilyDAO.insertSelective(family);
        //创建一个创立者和刚刚创建的家庭的映射
        FamilyMappingKey key = new FamilyMappingKey();
        key.setUserId(id);
        key.setFamilyId(family.getId());
        familyMappingDAO.insert(key);
        return family;
    }

    @Override
    public AccFamily update(FamilyDto familyDto) {
        AccFamily family = new AccFamily(familyDto);
        accFamilyDAO.updateByPrimaryKeySelective(family);
        return accFamilyDAO.selectByPrimaryKey(familyDto.getId());
    }

    @Override
    public AccFamily selectById(Long id) {
        return accFamilyDAO.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<AccFamily> selectAll(Long userId) {
        return accFamilyDAO.selectAllFamilyByUser(userId);
    }

    @Override
    public PageDto selectByPage(Integer rows, Integer page, Long userId) {
        PageHelper.startPage(page, rows);
        List<AccFamily> accFamilies = accFamilyDAO.selectAllFamilyByUser(userId);
        PageInfo<AccFamily> pageInfo = new PageInfo<>(accFamilies);
        return new PageDto(pageInfo.getPageSize(),pageInfo.getPageNum(),pageInfo.getPages(),accFamilies);
    }

    @Override
    public List<UserDto> selectAllUserByFamily(Long familyId) {
        List<AccUser> selectList = accUserDAO.selectAllUserByFamily(familyId);
        List<UserDto> dtoList = new ArrayList<>();
        for (AccUser accUser : selectList) {
            dtoList.add(new UserDto(accUser));
        }
        return dtoList;
    }

    @Override
    public AccFamily joinFamily(Long familyId, Long useId) {
        FamilyMappingKey key = new FamilyMappingKey();
        key.setFamilyId(familyId);
        key.setUserId(useId);
        familyMappingDAO.insert(key);
        return accFamilyDAO.selectByPrimaryKey(familyId);
    }

    @Override
    public AccFamily quitFamily(Long familyId, Long useId) {
        FamilyMappingKey key = new FamilyMappingKey();
        key.setFamilyId(familyId);
        key.setUserId(useId);
        familyMappingDAO.deleteByPrimaryKey(key);
        return accFamilyDAO.selectByPrimaryKey(familyId);
    }
}
