package com.ojt.toyproject.rent;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class RentDto {
    private Long seq;
    private Long book_seq;
    private String member_id;
    private LocalDateTime rent_date;
    private LocalDateTime due_date;
    private LocalDateTime return_date;
    private boolean is_returned;

}
