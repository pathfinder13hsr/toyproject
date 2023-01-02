package com.ojt.toyproject.booking;

import com.ojt.toyproject.SearchDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;
    private final BookingService bookingService3;

    public BookingController(@Qualifier("Impl1") BookingService bookingService, @Qualifier("Impl3_JPA") BookingService bookingService3) {
        this.bookingService = bookingService;
        this.bookingService3 = bookingService3;
    }

    //예약하기(Booking 테이블 insert)
    @PostMapping
    public void makeBooking(@RequestBody BookingDto bookingDto) {
        bookingService3.makeBooking(bookingDto);
    }

    //예약 리스트 조회
    @GetMapping
    public List<BookingDto> getBookingList(@RequestBody(required = false) SearchDto searchDto){
        List<BookingDto> bookingDtoList = bookingService3.getBookingList(searchDto);
        return bookingDtoList;
    }
}
