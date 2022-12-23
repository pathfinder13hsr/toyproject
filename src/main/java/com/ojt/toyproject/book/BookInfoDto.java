package com.ojt.toyproject.book;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookInfoDto {
    private Long isbn;
    private int category;
    private String title;
    private String author;
    private String publisher;
    private Long totalRentCount;


    private int totalStockCount;
    private int rentCount;
    private int availableCount;
}
