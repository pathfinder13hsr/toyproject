package com.ojt.toyproject.rent;

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


    public RentEntity byInsert() {
        return RentEntity
                .builder()
                .bookSeq(this.bookSeq)
                .memberId(this.memberId)
                .rentDate(LocalDate.now())
                .dueDate(LocalDate.now().plusDays(7))
                .isReturned("N")
                .build();
    }
}
