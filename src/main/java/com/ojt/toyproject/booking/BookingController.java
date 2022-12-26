package com.ojt.toyproject.booking;

import com.ojt.toyproject.SearchDto;
import com.ojt.toyproject.book.BookDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    //예약하기(Booking 테이블 insert)
    @PostMapping
    public void makeBooking(@RequestBody BookingDto bookingDto) {
        bookingService.makeBooking(bookingDto);
    }

    @GetMapping
    public List<BookingDto> getBookingList(@RequestBody(required = false) SearchDto searchDto){
        List<BookingDto> bookingDtoList = bookingService.getBookingList(searchDto);
        return bookingDtoList;
    }
}
