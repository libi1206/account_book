package com.libi.accountbook.dto;

import java.io.Serializable;

/**
 * @author libi
 * 用于记录查询交易记录时的条件
 */
public class RecordQueryConditionDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;
    private Long otherUserId;
    private Long assetsId;
    private Long otherAssetsId;
    private Long accountId;
    private Long typeId;
    private Long familyId;
    private Long treasuryId;
    private Boolean income;

    public Long getOtherAssetsId() {
        return otherAssetsId;
    }

    public void setOtherAssetsId(Long otherAssetsId) {
        this.otherAssetsId = otherAssetsId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOtherUserId() {
        return otherUserId;
    }

    public void setOtherUserId(Long otherUserId) {
        this.otherUserId = otherUserId;
    }

    public Long getAssetsId() {
        return assetsId;
    }

    public void setAssetsId(Long assetsId) {
        this.assetsId = assetsId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }

    public Long getTreasuryId() {
        return treasuryId;
    }

    public void setTreasuryId(Long treasuryId) {
        this.treasuryId = treasuryId;
    }

    public Boolean getIncome() {
        return income;
    }

    public void setIncome(Boolean income) {
        this.income = income;
    }
}
