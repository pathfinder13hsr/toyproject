package com.ojt.toyproject.booking;

import com.ojt.toyproject.SearchDto;

import java.util.List;

public interface BookingService {
    void makeBooking (BookingDto bookingDto);
    List<BookingDto> getBookingList (SearchDto searchDto);
    void deleteBooking (Long seq);
    int getBookingRownum(Long isbn, Long bookingSeq);
    void updateIsRented(Long seq);

}
