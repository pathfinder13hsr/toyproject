package com.ojt.toyproject.booking;

import com.ojt.toyproject.book.bookInfo.BookInfoEntity;
import com.ojt.toyproject.member.MemberEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
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
        this.isbn = bookingEntity.getBookInfoEntity().getIsbn();
        this.memberId = bookingEntity.getMemberEntity().getMemberId();
        this.bookingDatetime = bookingEntity.getBookingDatetime();
        this.isRented = bookingEntity.getIsRented();
    }


    public BookingEntity byInsert(){
        return BookingEntity
                .builder()
                .seq(this.seq)
                .bookInfoEntity(BookInfoEntity.builder().isbn(this.isbn).build())
                .memberEntity(MemberEntity.builder().memberId(this.memberId).build())
                .bookingDatetime(LocalDateTime.now())
                .isRented("N")
                .build();
    }




}
