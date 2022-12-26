package com.ojt.toyproject;

import lombok.Data;

@Data
public class Pagination {
    private int limitStart;

    public Pagination(SearchDto searchDto) {
        this.limitStart = (searchDto.getPage() - 1) * searchDto.getRecordSize();
    }
}
