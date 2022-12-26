package com.ojt.toyproject.booking;

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

}
