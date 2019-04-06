package com.libi.accountbook.dto;

import com.libi.accountbook.entity.AccAssets;

import java.io.Serializable;

/**
 * @author libi
 */
public class AssetsDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String assetsName;
    private Double moner;
    private String note;
    private Boolean oneWay;

    public AssetsDto(AccAssets assets) {
        setId(assets.getId());
        setAssetsName(assets.getAssetsName());
        setMoner(assets.getMoner());
        setNote(assets.getNote());
        setOneWay(assets.getOneWay());
    }

    public AssetsDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getOneWay() {
        return oneWay;
    }

    public void setOneWay(Boolean oneWay) {
        this.oneWay = oneWay;
    }
}
