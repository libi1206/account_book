package com.libi.accountbook.dto;

import com.libi.accountbook.entity.AccTransactionType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author libi
 */
public class TransactionTypeDto implements Serializable {
    private Long id;
    private Long userId;
    private Long parentId;
    private String typeName;
    private Boolean income;
    private String note;
    private Long createTime;
    private List<TransactionTypeDto> childType = new ArrayList<>();
    private static final long serialVersionUID = 1L;

    public TransactionTypeDto() {
    }

    public TransactionTypeDto(AccTransactionType accTransactionType) {
        setParentId(accTransactionType.getParentId());
        setId(accTransactionType.getId());
        setUserId(accTransactionType.getUserId());
        setTypeName(accTransactionType.getTypeName());
        setCreateTime(accTransactionType.getCreateTime());
        setIncome(accTransactionType.getIncome());
        setNote(accTransactionType.getNote());
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<TransactionTypeDto> getChildType() {
        return childType;
    }

    public void setChildType(TransactionTypeDto childType) {
        this.childType.add(childType);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Boolean getIncome() {
        return income;
    }

    public void setIncome(Boolean income) {
        this.income = income;
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
