package com.ojt.toyproject.booking;

import com.ojt.toyproject.SearchDto;
import com.ojt.toyproject.book.BookService;
import com.ojt.toyproject.book.bookInfo.BookInfoDto;
import com.ojt.toyproject.book.bookInfo.BookInfoEntity;
import com.ojt.toyproject.member.MemberDto;
import com.ojt.toyproject.member.MemberEntity;
import com.ojt.toyproject.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("Impl3_JPA")
@Slf4j
public class BookingServiceImpl3 implements BookingService{
    private final BookingRepository bookingRepository;
    private final BookService bookService3;
    private final BookingRepositorySupport bookingRepositorySupport;
    private final BookingMapper bookingMapper;
    private final MemberService memberService3;


    public BookingServiceImpl3(BookingRepository bookingRepository, @Qualifier("Impl3_JPA") BookService bookService3, BookingRepositorySupport bookingRepositorySupport, BookingMapper bookingMapper, @Qualifier("Impl3_JPA") MemberService memberService3) {
        this.bookingRepository = bookingRepository;
        this.bookService3 = bookService3;
        this.bookingRepositorySupport = bookingRepositorySupport;
        this.bookingMapper = bookingMapper;
        this.memberService3 = memberService3;
    }

    @Override
    public void makeBooking(BookingDto bookingDto) {
        int availableCountByIsbn = bookService3.getInStockCountByIsbn(bookingDto.getIsbn());
        MemberEntity memberEntity = memberService3.getMemberById(bookingDto.getMemberId()).bySelect();
        BookInfoEntity bookInfoEntity = bookService3.getBookInfoByIsbn(bookingDto.getIsbn()).bySelect();
        log.info("availableCountByIsbn>>>"+availableCountByIsbn);
        if (!bookingRepository.existsByBookInfoEntityAndMemberEntity(bookInfoEntity, memberEntity)){ // 예약 테이블에 동일 isbn, memberId가 없고
            if (availableCountByIsbn == 0) { //대출 가능수량이 0이면
                bookingRepository.save(bookingDto.byInsert()); // 예약 신청 가능
            } else {
                log.info("해당 도서는 즉시 대출 가능"); //대출 가능 수량이 0이 아니면 즉시 대출 가능, 예약 신청 불가
            }
        } else {
            log.info("이미 예약신청 했음"); // 예약 테이블에 동일 isbn, memberId가 있으면 예약 신청 불가
        }

    }

    @Override
    public List<BookingDto> getBookingList(SearchDto searchDto) {
        List<BookingDto> bookingDtoList = bookingRepositorySupport.getBookingList(searchDto);
        return bookingDtoList;
    }

    @Override
    public void deleteBooking(Long seq) {
        bookingRepository.deleteById(seq);
    }

    @Override
    public int getBookingRownum(Long isbn, Long bookingSeq) {
        List<BookingEntity> bookingEntityList = bookingRepository.getBookingRownum(isbn);
        List<BookingDto> bookingDtoList = new ArrayList<>();
        for (BookingEntity bookingEntity : bookingEntityList) {
            BookingDto bookingDto = BookingDto.byEntity().bookingEntity(bookingEntity).build();
            bookingDtoList.add(bookingDto);
        }
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
        bookingRepositorySupport.updateIsRented(seq);
    }


}
