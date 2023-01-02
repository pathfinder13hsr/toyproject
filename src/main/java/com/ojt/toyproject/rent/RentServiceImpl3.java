package com.ojt.toyproject.rent;

import com.ojt.toyproject.SearchDto;
import com.ojt.toyproject.book.BookService;
import com.ojt.toyproject.book.book.BookDto;
import com.ojt.toyproject.booking.BookingDto;
import com.ojt.toyproject.booking.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Qualifier("Impl3_JPA")
@Slf4j
public class RentServiceImpl3 implements RentService{
    private final RentRepositoty rentRepositoty;
    private final BookService bookService3;
    private final BookingService bookingService3;
    private final RentRepositorySupport rentRepositorySupport;

    public RentServiceImpl3(RentRepositoty rentRepositoty, @Qualifier("Impl3_JPA") BookService bookService3, @Qualifier("Impl3_JPA") BookingService bookingService3, RentRepositorySupport rentRepositorySupport) {
        this.rentRepositoty = rentRepositoty;
        this.bookService3 = bookService3;
        this.bookingService3 = bookingService3;
        this.rentRepositorySupport = rentRepositorySupport;
    }

    @Override
    public void rentBook(RentDto rentDto) {

        //rentDto(bookSeq, memberId)의 bookSeq로 isbn 가져오기
        SearchDto searchDto = new SearchDto();
        searchDto.setSearchType("seq");
        String keyword = rentDto.getBookSeq().toString();
        searchDto.setKeyword(keyword);
        Long isbn = bookService3.getBookList(searchDto).get(0).getIsbn();

        //isbn으로 해당 도서 전체 재고 수량 조회
        int inStockCount = bookService3.getInStockCountByIsbn(isbn);
        log.info("inStockCount>>"+inStockCount);

        if(inStockCount == 0){
            log.info("재고 없음, 대출 불가");
        }else {
            int bookingCount = bookService3.getBookingCountByIsbn(isbn); // isbn으로 예약 수량 조회
            log.info("bookingCount>>>"+bookingCount);
            if (bookingCount == 0){ //예약 수량이 0이면 바로 대출 가능
                rentRepositoty.save(rentDto.byInsert());
                bookService3.addRentCount(rentDto.getBookSeq());
                bookService3.changeBookStatusToN(rentDto.getBookSeq());
            }else{ //예약 수량이 0이 아니면
                //isbn으로 예약 리스트 조회
                SearchDto searchDto1 = new SearchDto();
                searchDto1.setSearchType("isbn");
                searchDto1.setKeyword(isbn.toString());
                List<BookingDto> bookingList = bookingService3.getBookingList(searchDto1);

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
                    int bookingRownum = bookingService3.getBookingRownum(isbn, bookingSeq); //예약 seq로 예약 순번 조회
                    log.info("bookingRownum>>>"+bookingRownum);
                    if (inStockCount >= bookingRownum){ //재고 수량이 내 순번보다 같거나 크면 바로 대출 가능
                        rentRepositoty.save(rentDto.byInsert());
                        bookService3.addRentCount(rentDto.getBookSeq());
                        bookService3.changeBookStatusToN(rentDto.getBookSeq());
                        bookingService3.updateIsRented(bookingSeq);
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
        rentRepositorySupport.updateRent(seq); // rent 테이블 반납완료로 변경
        bookService3.changeBookStatusToY(seq); //book 테이블 isInStock Y로 변경

    }

    @Override
    public Map<String, List<RentDto>> getRentListById(String id) {

        Map<String, List<RentDto>> rentListMap = new HashMap<>();
        List<RentDto> rentList = rentRepositorySupport.getOnesRentList(id);
        List<RentDto> returnList = rentRepositorySupport.getOnesReturnList(id);
        rentListMap.put("대출중", rentList);
        rentListMap.put("반납완료", returnList);
        return rentListMap;
    }
}
