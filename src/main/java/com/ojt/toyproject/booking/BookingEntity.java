package com.ojt.toyproject.booking;

import com.ojt.toyproject.book.book.BookEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@ToString
@Entity(name = "booking")
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class BookingEntity {
    @Id
    private Long seq;
    private Long isbn;
    private String memberId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime bookingDatetime;
    private String isRented;

    @Builder
    public BookingEntity(Long seq, Long isbn, String memberId, LocalDateTime bookingDatetime, String isRented) {
        this.seq = seq;
        this.isbn = isbn;
        this.memberId = memberId;
        this.bookingDatetime = bookingDatetime;
        this.isRented = isRented;
    }



}
