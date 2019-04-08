package com.libi.accountbook.dto;

import com.libi.accountbook.entity.*;

import java.io.Serializable;

/**
 * @author libi
 */
public class RecordDto implements Serializable {
    private Long id;
    private AccAssets assetsId;
    private UserDto user;
    private UserDto otherUser;
    private AccAssets otherAssets;
    private AccTransactionType type;
    private AccAccount account;
    private AccTreasury treasury;
    private Double amount;
    private String note;
    private Long createTime;
    private static final long serialVersionUID = 1L;

    public RecordDto(AccTransactionRecord record) {
        this.id = record.getId();
        this.amount = record.getAmount();
        this.note = record.getNote();
        this.createTime = record.getCreateTime();
    }

    public RecordDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccAssets getAssetsId() {
        return assetsId;
    }

    public void setAssetsId(AccAssets assetsId) {
        this.assetsId = assetsId;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(AccUser user) {
        this.user = new UserDto(user);
    }

    public UserDto getOtherUser() {
        return otherUser;
    }

    public void setOtherUser(AccUser otherUser) {
        this.otherUser = new UserDto(otherUser);
    }

    public AccAssets getOtherAssets() {
        return otherAssets;
    }

    public void setOtherAssets(AccAssets otherAssets) {
        this.otherAssets = otherAssets;
    }

    public AccTransactionType getType() {
        return type;
    }

    public void setType(AccTransactionType type) {
        this.type = type;
    }

    public AccAccount getAccount() {
        return account;
    }

    public void setAccount(AccAccount account) {
        this.account = account;
    }

    public AccTreasury getTreasury() {
        return treasury;
    }

    public void setTreasury(AccTreasury treasury) {
        this.treasury = treasury;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
