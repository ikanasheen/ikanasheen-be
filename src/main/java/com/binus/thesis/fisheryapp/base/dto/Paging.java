package com.binus.thesis.fisheryapp.base.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonView;

@JsonInclude(Include.NON_NULL)
@JsonView(Views.Basic.class)
public class Paging implements BaseInterface {

    private static final long serialVersionUID = -9127618400914496104L;

    public static final Integer DEFAULT_LIMIT = 10;
    public static final Integer DEFAULT_PAGE = 1;

    private Integer page;

    private Integer limit;

    private Integer totalPage;

    private long totalRecord;

    public Paging() {
        this.page = DEFAULT_PAGE;
        this.limit = DEFAULT_LIMIT;
    }

    public Paging(Integer limit) {
        this(DEFAULT_PAGE, limit);
    }

    public Paging(Integer page, Integer limit) {
        this.page = page;
        this.limit = limit;
    }

    public static Paging initialize() {
        return new Paging();
    }

    public static Paging validate(Paging paging) {
        if (paging.getPage() == 0)
            paging.setPage(DEFAULT_PAGE);
        if (paging.getLimit() == 0)
            paging.setLimit(DEFAULT_LIMIT);
        return paging;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(long totalRecord) {
        this.totalRecord = totalRecord;
    }

    @Override
    public String toString() {
        return "Paging{" +
                "page='" + page + '\'' +
                ", limit='" + limit + '\'' +
                ", totalpage='" + totalPage + '\'' +
                ", totalrecord='" + totalRecord + '\'' +
                '}';
    }
}
