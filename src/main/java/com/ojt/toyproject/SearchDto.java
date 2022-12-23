package com.ojt.toyproject;

import lombok.Data;

@Data
public class SearchDto {
    private String searchType;
    private String keyword;
    private String order;
}
