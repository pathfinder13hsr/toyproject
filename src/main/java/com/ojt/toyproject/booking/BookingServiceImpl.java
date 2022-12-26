package com.ojt.toyproject.booking;

import com.ojt.toyproject.SearchDto;
import com.ojt.toyproject.book.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService{
    private final BookingMapper bookingMapper;
    private final BookService bookService;

    public BookingServiceImpl(BookingMapper bookingMapper, BookService bookService) {
        this.bookingMapper = bookingMapper;
        this.bookService = bookService;
    }

    @Override
    public void makeBooking(BookingDto bookingDto) {
        int availableCountByIsbn = bookService.getAvailableCountByIsbn(bookingDto.getIsbn());
        if (!bookingMapper.isBookedAlready(bookingDto)){
            if (availableCountByIsbn == 0) {
                bookingMapper.makeBooking(bookingDto);
            } else {
                log.info("해당 도서는 즉시 대출 가능");
            }
        } else {
            log.info("이미 예약신청 했음");
        }

    }

    @Override
    public List<BookingDto> getBookingList(SearchDto searchDto) {
        List<BookingDto> bookingDtoList = bookingMapper.getBookingList(searchDto);
        return bookingDtoList;
    }

    @Override
    public void deleteBooking(Long seq) {

    }
}
