package com.ojt.toyproject.rent;

import com.ojt.toyproject.SearchDto;
import com.ojt.toyproject.book.BookService;
import com.ojt.toyproject.booking.BookingDto;
import com.ojt.toyproject.booking.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Qualifier("Impl1")
public class RentServiceImpl implements RentService{
    private final RentMapper rentMapper;
    private final BookService bookService; //BookMapper 또는 BookService 주입, 서비스 불러오는게 더 적절
    private final BookService bookService3;
    private final BookingService bookingService;
    public RentServiceImpl(RentMapper rentMapper, @Qualifier("Impl1") BookService bookService, @Qualifier("Impl3_JPA") BookService bookService3, @Qualifier("Impl1") BookingService bookingService) {
        this.rentMapper = rentMapper;
        this.bookService = bookService;
        this.bookService3 = bookService3;
        this.bookingService = bookingService;
    }

    @Override
    public void rentBook(RentDto rentDto) {
        //rentDto(bookSeq, memberId)의 bookSeq로 isbn 가져오기
        SearchDto searchDto = new SearchDto();
        searchDto.setSearchType("seq");
        String keyword = rentDto.getBookSeq().toString();
        searchDto.setKeyword(keyword);
        Long isbn = bookService.getBookList(searchDto).get(0).getIsbn();

        //isbn으로 해당 도서 전체 재고 수량 조회
        int inStockCount = bookService.getInStockCountByIsbn(isbn);
        log.info("inStockCount>>>>"+inStockCount);


        if(inStockCount == 0){
            log.info("재고 없음, 대출 불가");
        }else {
            int bookingCount = bookService.getBookingCountByIsbn(isbn); // isbn으로 예약 수량 조회
            log.info("bookingCount>>>"+bookingCount);
            if (bookingCount == 0){ //예약 수량이 0이면 바로 대출 가능
                rentMapper.insertRent(rentDto);
                bookService.addRentCount(rentDto.getBookSeq());
                bookService.changeBookStatusToN(rentDto.getBookSeq());
            }else{ //예약 수량이 0이 아니면
                //isbn으로 예약 리스트 조회
                SearchDto searchDto1 = new SearchDto();
                searchDto1.setSearchType("isbn");
                searchDto1.setKeyword(isbn.toString());
                List<BookingDto> bookingList = bookingService.getBookingList(searchDto1);

                boolean chkBooking = false;
                int idx = 0;
                //예약 리스트에서 대출 신청자와 동일한 memberId가 있으면 해당 idx 출력, chkBooking=true
                for (int i=0; i<bookingList.size(); i++){
                    if (bookingList.get(i).getMemberId().equals(rentDto.getMemberId())){
                        idx = i;
                        chkBooking = true;
                    }
                }
                Long bookingSeq = bookingList.get(idx).getSeq(); //위에서 가져온 idx로 예약 seq 조회
                if(chkBooking){
                    int bookingRownum = bookingService.getBookingRownum(isbn, bookingSeq); //예약 seq로 예약 순번 조회
                    log.info("bookingRownum>>>"+bookingRownum);
                    if (inStockCount >= bookingRownum){ //재고 수량이 내 순번보다 같거나 크면 바로 대출 가능
                        rentMapper.insertRent(rentDto);
                        bookService.addRentCount(rentDto.getBookSeq());
                        bookService.changeBookStatusToN(rentDto.getBookSeq());
                        bookingService.updateIsRented(bookingSeq);
                    }else{
                        log.info("대출 가능한 수량보다 예약 순번이 뒤임");
                    }
                }else {
                    log.info("예약자 명단에 없음");
                }
            }
        }
    }

    @Override
    public void returnBook(Long seq) {
        rentMapper.updateRent(seq); // rent 테이블 반납완료로 변경
        bookService3.changeBookStatusToY(seq); //book 테이블 isInStock Y로 변경
    }

    //회원 id로 대출 내역 조회(마이페이지)
    @Override
    public Map<String, List<RentDto>> getRentListById(String id) {
        Map<String, List<RentDto>> rentListMap = new HashMap<>();
        List<RentDto> rentList = rentMapper.getOnesRentList(id);
        List<RentDto> returnList = rentMapper.getOnesReturnList(id);
        rentListMap.put("대출중", rentList);
        rentListMap.put("반납완료", returnList);
        return rentListMap;
    }
}
