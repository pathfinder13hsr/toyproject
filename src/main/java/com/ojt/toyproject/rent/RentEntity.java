package com.ojt.toyproject.rent;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@ToString
@Entity(name = "rent")
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class RentEntity {
    @Id
    private Long seq;
    private Long bookSeq;
    private String memberId;
    private LocalDate rentDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String isReturned;

    @Builder
    public RentEntity(Long seq, Long bookSeq, String memberId, LocalDate rentDate, LocalDate dueDate, LocalDate returnDate, String isReturned) {
        this.seq = seq;
        this.bookSeq = bookSeq;
        this.memberId = memberId;
        this.rentDate = rentDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.isReturned = isReturned;
    }
}
