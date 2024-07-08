package com.ef.emergency.dto;

import jakarta.validation.constraints.Min;

public class Page {
    @Min(0)
    private int pageNumber;
    @Min(0)
    private int pageSize = 10;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
