package com.ojt.toyproject.book;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class BookDto {
    private Long seq;
    private Long isbn;
    private int stockNum;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate stockDate;
    private String isAvailable;
}
