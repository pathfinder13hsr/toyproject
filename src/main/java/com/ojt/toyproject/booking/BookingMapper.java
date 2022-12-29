package com.ojt.toyproject.booking;

import com.ojt.toyproject.SearchDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookingMapper {
    void makeBooking(BookingDto bookingDto);
    boolean isBookedAlready(BookingDto bookingDto);
    List<BookingDto> getBookingList(SearchDto searchDto);
    List<BookingDto> getBookingRownum(Long isbn);
    void updateIsRented(Long seq);
    void deleteBooking(Long seq);
}
