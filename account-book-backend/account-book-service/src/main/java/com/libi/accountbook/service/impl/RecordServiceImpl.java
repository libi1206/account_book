package com.libi.accountbook.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.libi.accountbook.dao.AccTransactionRecordDAO;
import com.libi.accountbook.dto.AssetsDto;
import com.libi.accountbook.dto.PageDto;
import com.libi.accountbook.dto.RecordDto;
import com.libi.accountbook.dto.RecordQueryConditionDto;
import com.libi.accountbook.entity.AccAssets;
import com.libi.accountbook.entity.AccTransactionRecord;
import com.libi.accountbook.entity.AccTreasury;
import com.libi.accountbook.exception.AttrNotLoginUserException;
import com.libi.accountbook.exception.NoMoneyException;
import com.libi.accountbook.service.*;
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
public class RecordServiceImpl implements RecordService {
    @Autowired
    private AccTransactionRecordDAO accTransactionRecordDAO;
    @Autowired
    private TreasuryService treasuryService;
    @Autowired
    private AssetsService assetsService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionTypeService transactionTypeService;
    @Autowired
    private UserService userService;

    /**
     * 插入一条数据
     *
     * @param record
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public RecordDto insertRecord(AccTransactionRecord record, Long userId) throws AttrNotLoginUserException, NoMoneyException {
        record.setUserId(userId);
        if (record.getCreateTime() == null) {
            record.setCreateTime(System.currentTimeMillis());
        }
        //插入一条记录
        accTransactionRecordDAO.insertSelective(record);
        //取出资产和小金库，修改金额后重新插入
        AccAssets assets = assetsService.selectById(record.getAssetsId());
        Double result = assets.getMoner() + record.getAmount();
        if (result < 0) {
            throw new NoMoneyException();
        }
        assets.setMoner(result);
        assetsService.update(new AssetsDto(assets),userId);
        if (record.getTreasuryId() != null) {
            AccTreasury treasury = treasuryService.selectById(record.getTreasuryId());
            result = treasury.getMoner() + record.getAmount();
            if (result < 0) {
                throw new NoMoneyException();
            }
            treasury.setMoner(result);
            treasuryService.update(treasury,userId);
        }
        return recordEntityToDto(record);
    }

    /**
     * 根据记录Id选择一条数据
     *
     * @param recordId
     * @return
     */
    @Override
    public RecordDto selectById(Long recordId) {
        AccTransactionRecord accTransactionRecord = accTransactionRecordDAO.selectByPrimaryKey(recordId);
        return recordEntityToDto(accTransactionRecord);
    }

    /**
     * 通过RecordEntity注入属性给RecordDTO
     *
     * @param record
     */
    private RecordDto recordEntityToDto(AccTransactionRecord record) {
        RecordDto recordDto = new RecordDto(record);
        //用户
        if (record.getUserId() != null) {
            recordDto.setUser(userService.selectUserById(record.getUserId()));
        }
        //账本
        if (record.getAccountId() != null) {
            recordDto.setAccount(accountService.selectById(record.getAccountId()));
        }
        //资产
        if (record.getAssetsId() != null) {
            recordDto.setAssetsId(assetsService.selectById(record.getAssetsId()));
        }
        //类型
        if (record.getTypeId() != null) {
            recordDto.setType(transactionTypeService.selectById(record.getTypeId()));
        }
        //小金库
        if (record.getTreasuryId() != null) {
            recordDto.setTreasury(treasuryService.selectById(record.getTreasuryId()));
        }
        //对方用户
        if (record.getOtherUser() != null) {
            recordDto.setOtherUser(userService.selectUserById(record.getOtherUser()));
        }
        //对方资产
        if (record.getOtherAssets() != null) {
            recordDto.setOtherAssets(assetsService.selectById(record.getAssetsId()));
        }
        return recordDto;
    }

    /**
     * 根据传入的条件查询记录
     * 这应该可以写成一个SQL语句，但是为了不那么复杂，我这边先在Java端做操作
     *
     * @param recordQueryConditionDto 条件
     * @param userId                  当前用户的ID
     * @return 查询结果
     */
    @Override
    public PageDto selectByCondition(Integer page, Integer rows, RecordQueryConditionDto recordQueryConditionDto, Long userId) {
        recordQueryConditionDto.setUserId(userId);
        //开启分页
        PageHelper.startPage(page, rows);
        //查出满足其他条件的记录
        List<AccTransactionRecord> recordList = accTransactionRecordDAO.selectByCondition(recordQueryConditionDto);
        PageInfo<AccTransactionRecord> pageInfo = new PageInfo<>(recordList);


        List<RecordDto> resultList = new ArrayList<>();
        for (AccTransactionRecord record : pageInfo.getList()) {
            resultList.add(recordEntityToDto(record));
        }

        return new PageDto(pageInfo.getPageSize(),pageInfo.getPageNum(),pageInfo.getPages(),resultList);
    }

    /**
     * 根据ID更新记录
     */
    @Override
    public RecordDto updateById(Long recordId, String note, Long typeId, Long userId) throws AttrNotLoginUserException {
        RecordDto selected = selectById(recordId);
        if (!selected.getUser().getId().equals(userId)) {
            throw new AttrNotLoginUserException();
        }

        AccTransactionRecord record = new AccTransactionRecord();
        record.setId(recordId);
        record.setNote(note);
        record.setTypeId(typeId);
        return selectById(recordId);
    }


}
