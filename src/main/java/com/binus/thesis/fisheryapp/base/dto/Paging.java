package com.binus.thesis.fisheryapp.base.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonView;

@JsonInclude(Include.NON_NULL)
@JsonView(Views.Basic.class)
public class Paging implements BaseInterface {

    private static final long serialVersionUID = -9127618400914496104L;

    public static final int DEFAULT_LIMIT = 10;
    public static final int DEFAULT_PAGE = 1;

    private int page;

    private int limit;

    private int totalpage;

    private long totalrecord;

    public Paging() {
        this.page = DEFAULT_PAGE;
        this.limit = DEFAULT_LIMIT;
    }

    public Paging(int limit) {
        this(DEFAULT_PAGE, limit);
    }

    public Paging(int page, int limit) {
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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }

    public long getTotalrecord() {
        return totalrecord;
    }

    public void setTotalrecord(long totalrecord) {
        this.totalrecord = totalrecord;
    }

    @Override
    public String toString() {
        return "Paging{" +
                "page='" + page + '\'' +
                ", limit='" + limit + '\'' +
                ", totalpage='" + totalpage + '\'' +
                ", totalrecord='" + totalrecord + '\'' +
                '}';
    }
}
