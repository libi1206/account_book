package com.libi.accountbook.entity;

import com.libi.accountbook.dto.RecordQueryConditionDto;

import java.io.Serializable;

/**
 * acc_transaction_record
 * @author 
 */
public class AccTransactionRecord implements Serializable {
    private Long id;

    private Long assetsId;

    private Long userId;

    private Long otherUser;

    private Long otherAssets;

    private Long typeId;

    private Long accountId;

    private Long treasuryId;

    private Double amount;

    private String note;

    private Long createTime;

    private static final long serialVersionUID = 1L;

    public AccTransactionRecord() {
    }

    public AccTransactionRecord(RecordQueryConditionDto recordQueryConditionDto) {
        this.setUserId(recordQueryConditionDto.getUserId());
        this.setAssetsId(recordQueryConditionDto.getAssetsId());
        this.setOtherAssets(recordQueryConditionDto.getOtherAssetsId());
        this.setOtherUser(recordQueryConditionDto.getOtherUserId());
        this.setTreasuryId(recordQueryConditionDto.getTreasuryId());
        this.setAccountId(recordQueryConditionDto.getAccountId());
        this.setTypeId(recordQueryConditionDto.getTypeId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAssetsId() {
        return assetsId;
    }

    public void setAssetsId(Long assetsId) {
        this.assetsId = assetsId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOtherUser() {
        return otherUser;
    }

    public void setOtherUser(Long otherUser) {
        this.otherUser = otherUser;
    }

    public Long getOtherAssets() {
        return otherAssets;
    }

    public void setOtherAssets(Long otherAssets) {
        this.otherAssets = otherAssets;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getTreasuryId() {
        return treasuryId;
    }

    public void setTreasuryId(Long treasuryId) {
        this.treasuryId = treasuryId;
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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        AccTransactionRecord other = (AccTransactionRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAssetsId() == null ? other.getAssetsId() == null : this.getAssetsId().equals(other.getAssetsId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getOtherUser() == null ? other.getOtherUser() == null : this.getOtherUser().equals(other.getOtherUser()))
            && (this.getOtherAssets() == null ? other.getOtherAssets() == null : this.getOtherAssets().equals(other.getOtherAssets()))
            && (this.getTypeId() == null ? other.getTypeId() == null : this.getTypeId().equals(other.getTypeId()))
            && (this.getAccountId() == null ? other.getAccountId() == null : this.getAccountId().equals(other.getAccountId()))
            && (this.getTreasuryId() == null ? other.getTreasuryId() == null : this.getTreasuryId().equals(other.getTreasuryId()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAssetsId() == null) ? 0 : getAssetsId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getOtherUser() == null) ? 0 : getOtherUser().hashCode());
        result = prime * result + ((getOtherAssets() == null) ? 0 : getOtherAssets().hashCode());
        result = prime * result + ((getTypeId() == null) ? 0 : getTypeId().hashCode());
        result = prime * result + ((getAccountId() == null) ? 0 : getAccountId().hashCode());
        result = prime * result + ((getTreasuryId() == null) ? 0 : getTreasuryId().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", assetsId=").append(assetsId);
        sb.append(", userId=").append(userId);
        sb.append(", otherUser=").append(otherUser);
        sb.append(", otherAssets=").append(otherAssets);
        sb.append(", typeId=").append(typeId);
        sb.append(", accountId=").append(accountId);
        sb.append(", treasuryId=").append(treasuryId);
        sb.append(", amount=").append(amount);
        sb.append(", note=").append(note);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}