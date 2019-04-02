package com.libi.accountbook.dto;

import com.libi.accountbook.entity.AccUser;

import java.io.Serializable;

/**
 * @author libi
 */
public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String userName;
    private Integer sex;
    private String authority;
    private String neckName;
    private String headImg;
    private String phone;
    private Long createTime;

    public UserDto(AccUser user) {
        id = user.getId();
        userName = user.getUserName();
        sex = user.getSex();
        authority = user.getAuthority();
        neckName = user.getNeckName();
        headImg = user.getHeadImg();
        phone = user.getPhone();
        createTime = user.getCreateTime();
    }

    public UserDto() {
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getNeckName() {
        return neckName;
    }

    public void setNeckName(String neckName) {
        this.neckName = neckName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
