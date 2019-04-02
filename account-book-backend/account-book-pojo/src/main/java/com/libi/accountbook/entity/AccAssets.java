package com.libi.accountbook.entity;

import com.libi.accountbook.dto.AssetsDto;

import java.io.Serializable;
import java.util.Arrays;

/**
 * acc_assets
 * @author 
 */
public class AccAssets implements Serializable {
    private Long id;

    private Long userId;

    private String assetsName;

    private Double moner;

    private String note;

    private Long createTime;

    private Boolean oneWay;

    private static final long serialVersionUID = 1L;

    public AccAssets(AssetsDto assetsDto) {
        setId(assetsDto.getId());
        setAssetsName(assetsDto.getAssetsName());
        setNote(assetsDto.getNote());
        setMoner(assetsDto.getMoner());
        setOneWay(assetsDto.getOneWay());
    }

    public AccAssets() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAssetsName() {
        return assetsName;
    }

    public void setAssetsName(String assetsName) {
        this.assetsName = assetsName;
    }

    public Double getMoner() {
        return moner;
    }

    public void setMoner(Double moner) {
        this.moner = moner;
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

    public Boolean getOneWay() {
        return oneWay;
    }

    public void setOneWay(Boolean oneWay) {
        this.oneWay = oneWay;
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
        AccAssets other = (AccAssets) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getAssetsName() == null ? other.getAssetsName() == null : this.getAssetsName().equals(other.getAssetsName()))
            && (this.getMoner() == null ? other.getMoner() == null : this.getMoner().equals(other.getMoner()))
            && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getAssetsName() == null) ? 0 : getAssetsName().hashCode());
        result = prime * result + ((getMoner() == null) ? 0 : getMoner().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getOneWay()==null)? 0 : getOneWay().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", assetsName=").append(assetsName);
        sb.append(", moner=").append(moner);
        sb.append(", note=").append(note);
        sb.append(", createTime=").append(createTime);
        sb.append(", oneWay=").append(oneWay);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}