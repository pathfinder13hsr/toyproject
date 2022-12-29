package com.ojt.toyproject;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchDto {
    private String searchType;
    private String keyword;
    private String order;

    private Integer page; //현재 페이지 번호
    private Integer recordSize; //페이지당 출력할 데이터 개수

    private Pagination pagination;

//    public SearchDto() {
//        this.page = 1;
//        this.recordSize = 10;
//    }


}
