package com.ojt.toyproject.rent;

import com.ojt.toyproject.book.BookDto;
import com.ojt.toyproject.book.BookInfoDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class RentDto {
    private Long seq;
    private Long bookSeq;
    private String memberId;
    private LocalDate rentDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String isReturned;


}
