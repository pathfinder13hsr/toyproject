package com.ojt.toyproject.book;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BookDto {
    private Long seq;
    private int isbn;
    private int stockNum;
    private LocalDateTime stockDate;
    private boolean isAvailable;
}
