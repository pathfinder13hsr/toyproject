package com.ojt.toyproject.booking;

import com.ojt.toyproject.SearchDto;
import com.ojt.toyproject.book.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Qualifier("Impl1")
public class BookingServiceImpl implements BookingService{
    private final BookingMapper bookingMapper;
    private final BookService bookService;

    public BookingServiceImpl(BookingMapper bookingMapper, @Qualifier("Impl1") BookService bookService) {
        this.bookingMapper = bookingMapper;
        this.bookService = bookService;
    }

    @Override
    public void makeBooking(BookingDto bookingDto) {
        int availableCountByIsbn = bookService.getInStockCountByIsbn(bookingDto.getIsbn());
        if (!bookingMapper.isBookedAlready(bookingDto)){ // 예약 테이블에 동일 isbn, memberId가 없고
            if (availableCountByIsbn == 0) { //대출 가능수량이 0이면
                bookingMapper.makeBooking(bookingDto); // 예약 신청 가능
            } else {
                log.info("해당 도서는 즉시 대출 가능"); //대출 가능 수량이 0이 아니면 즉시 대출 가능, 예약 신청 불가
            }
        } else {
            log.info("이미 예약신청 했음"); // 예약 테이블에 동일 isbn, memberId가 있으면 예약 신청 불가
        }

    }

    @Override
    public List<BookingDto> getBookingList(SearchDto searchDto) {
        List<BookingDto> bookingDtoList = bookingMapper.getBookingList(searchDto);
        return bookingDtoList;
    }

    @Override
    public void deleteBooking(Long seq) {
        bookingMapper.deleteBooking(seq);
    }

    @Override
    public int getBookingRownum(Long isbn, Long bookingSeq) {
        List<BookingDto> bookingDtoList = bookingMapper.getBookingRownum(isbn);
        int bookingRownum = 0;
        for (int i=0; i<bookingDtoList.size(); i++){
            if(bookingDtoList.get(i).getSeq().equals(bookingSeq)){
                bookingRownum = bookingDtoList.get(i).getRownum();
            }
        }

        return bookingRownum;
    }

    @Override
    public void updateIsRented(Long seq) {
        bookingMapper.updateIsRented(seq);
    }
}
