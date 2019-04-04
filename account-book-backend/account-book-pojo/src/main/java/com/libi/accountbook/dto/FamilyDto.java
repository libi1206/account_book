package com.libi.accountbook.dto;

import java.io.Serializable;

/**
 * @author libi
 */
public class FamilyDto implements Serializable {
    private Long id;
    private String familyName;
    private String note;
    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
