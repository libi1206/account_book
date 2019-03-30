package com.libi.accountbook.entity;

import java.io.Serializable;
import java.util.Arrays;

/**
 * acc_transaction_record
 * @author 
 */
public class AccTransactionRecord implements Serializable {
    private Long id;

    private Long assetsId;

    private Long userId;

    private Long otherUser;

    private Long familyId;

    private Long typeId;

    private Long accountId;

    private Long treasuryId5;

    private Long amount;

    private String note;

    private Long createTime;

    private byte[] recoedType;

    private static final long serialVersionUID = 1L;

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

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
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

    public Long getTreasuryId5() {
        return treasuryId5;
    }

    public void setTreasuryId5(Long treasuryId5) {
        this.treasuryId5 = treasuryId5;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
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

    public byte[] getRecoedType() {
        return recoedType;
    }

    public void setRecoedType(byte[] recoedType) {
        this.recoedType = recoedType;
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
            && (this.getFamilyId() == null ? other.getFamilyId() == null : this.getFamilyId().equals(other.getFamilyId()))
            && (this.getTypeId() == null ? other.getTypeId() == null : this.getTypeId().equals(other.getTypeId()))
            && (this.getAccountId() == null ? other.getAccountId() == null : this.getAccountId().equals(other.getAccountId()))
            && (this.getTreasuryId5() == null ? other.getTreasuryId5() == null : this.getTreasuryId5().equals(other.getTreasuryId5()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (Arrays.equals(this.getRecoedType(), other.getRecoedType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAssetsId() == null) ? 0 : getAssetsId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getOtherUser() == null) ? 0 : getOtherUser().hashCode());
        result = prime * result + ((getFamilyId() == null) ? 0 : getFamilyId().hashCode());
        result = prime * result + ((getTypeId() == null) ? 0 : getTypeId().hashCode());
        result = prime * result + ((getAccountId() == null) ? 0 : getAccountId().hashCode());
        result = prime * result + ((getTreasuryId5() == null) ? 0 : getTreasuryId5().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + (Arrays.hashCode(getRecoedType()));
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
        sb.append(", familyId=").append(familyId);
        sb.append(", typeId=").append(typeId);
        sb.append(", accountId=").append(accountId);
        sb.append(", treasuryId5=").append(treasuryId5);
        sb.append(", amount=").append(amount);
        sb.append(", note=").append(note);
        sb.append(", createTime=").append(createTime);
        sb.append(", recoedType=").append(recoedType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}