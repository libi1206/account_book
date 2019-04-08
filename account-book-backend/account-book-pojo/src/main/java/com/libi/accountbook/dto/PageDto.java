package com.libi.accountbook.dto;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * @author libi
 */
public class PageDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer rows;
    private Integer nowPage;
    private Integer allPage;
    private List list;

    public PageDto(Integer rows, Integer nowPage, Integer allPage, List list) {
        this.rows = rows;
        this.nowPage = nowPage;
        this.allPage = allPage;
        this.list = list;
    }

    public PageDto(PageInfo pageInfo) {
        setRows(pageInfo.getPageSize());
        setNowPage(pageInfo.getPageNum());
        setAllPage(pageInfo.getPages());
        setList(pageInfo.getList());
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getNowPage() {
        return nowPage;
    }

    public void setNowPage(Integer nowPage) {
        this.nowPage = nowPage;
    }

    public Integer getAllPage() {
        return allPage;
    }

    public void setAllPage(Integer allPage) {
        this.allPage = allPage;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
