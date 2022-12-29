package com.ojt.toyproject.booking;

import com.ojt.toyproject.book.book.BookDto;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class BookingDto {
    private Long seq;
    private Long isbn;
    private String memberId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime bookingDatetime;
    private String isRented;

    private int rownum;


    @Builder(builderClassName = "byEntity", builderMethodName = "byEntity")
    public BookingDto(BookingEntity bookingEntity) {
        this.seq = bookingEntity.getSeq();
        this.isbn = bookingEntity.getIsbn();
        this.memberId = bookingEntity.getMemberId();
        this.bookingDatetime = bookingEntity.getBookingDatetime();
        this.isRented = bookingEntity.getIsRented();
    }

    public BookingEntity byInsert(){
        return BookingEntity
                .builder()
                .seq(this.seq)
                .isbn(this.isbn)
                .memberId(this.memberId)
                .bookingDatetime(LocalDateTime.now())
                .isRented("N")
                .build();
    }

    public BookingEntity byUpdate(BookingEntity bookingEntity){
        return BookingEntity
                .builder()
                .seq(bookingEntity.getSeq())
                .isbn(bookingEntity.getIsbn())
                .memberId(bookingEntity.getMemberId())
                .bookingDatetime(bookingEntity.getBookingDatetime())
                .isRented(this.isRented)
                .build();
    }


}
