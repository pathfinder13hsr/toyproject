package com.ojt.toyproject.booking;

import com.ojt.toyproject.book.book.BookEntity;
import com.ojt.toyproject.book.bookInfo.BookInfoEntity;
import com.ojt.toyproject.member.MemberEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString
@Entity(name = "booking")
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime bookingDatetime;
    private String isRented;

    @ManyToOne
    @JoinColumn(name = "isbn")
    private BookInfoEntity bookInfoEntity;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private MemberEntity memberEntity;







}
