package com.ojt.toyproject.booking;

import com.ojt.toyproject.SearchDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("Impl3_JPA")
@Slf4j
public class BookingServiceImpl3 implements BookingService{
    private final BookingRepository bookingRepository;

    public BookingServiceImpl3(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void makeBooking(BookingDto bookingDto) {

    }

    @Override
    public List<BookingDto> getBookingList(SearchDto searchDto) {
        return null;
    }

    @Override
    public void deleteBooking(Long seq) {

    }

    @Override
    public int getBookingRownum(Long isbn, Long bookingSeq) {
        return 0;
    }

    @Override
    public void updateIsRented(Long seq) {

    }


}
