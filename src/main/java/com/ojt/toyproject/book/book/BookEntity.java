package com.ojt.toyproject.book.book;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@ToString
@Entity(name = "book")
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    private Long isbn;
    private int stockNum;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate stockDate;
    private String isInStock;

    @Builder
    public BookEntity(Long seq, Long isbn, int stockNum, LocalDate stockDate, String isInStock) {
        this.seq = seq;
        this.isbn = isbn;
        this.stockNum = stockNum;
        this.stockDate = stockDate;
        this.isInStock = isInStock;
    }
}
