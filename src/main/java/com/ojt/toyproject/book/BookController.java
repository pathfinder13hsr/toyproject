package com.ojt.toyproject.book;

import com.ojt.toyproject.SearchDto;
import com.ojt.toyproject.book.book.BookDto;
import com.ojt.toyproject.book.bookInfo.BookInfoDto;
import com.ojt.toyproject.book.category.CategoryDto;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping
@Slf4j
public class BookController {
    private final BookService bookService;
    private final BookService bookService3;
    private final BookFeignClient bookFeignClient;

    public BookController(@Qualifier("Impl1") BookService bookService, @Qualifier("Impl3_JPA") BookService bookService3, BookFeignClient bookFeignClient) {
        this.bookService = bookService;
        this.bookService3 = bookService3;
        this.bookFeignClient = bookFeignClient;
    }

    //도서정보 : book_info, 소장도서 : book, 도서카테고리 : category ->세 테이블 CRUD

    //도서정보 등록
    @PostMapping("/book-info")
    public void insertBookInfo(@RequestBody BookInfoDto bookInfoDto){
        bookService3.insertBookInfo(bookInfoDto);
    }

    //도서정보 조회(모든 도서 정보)
    @GetMapping("/book-info")
    public List<BookInfoDto> getBookInfoList(@RequestBody(required = false) SearchDto searchDto){
        List<BookInfoDto> bookInfoDtoList = bookService3.getBookInfoList(searchDto);
        return bookInfoDtoList;
    }

    //도서정보 수정
    @PutMapping("/book-info")
    public void updateBookInfo(@RequestBody BookInfoDto bookInfoDto){
        bookService3.updateBookInfo(bookInfoDto);
    }

    //도서정보 삭제
    @DeleteMapping("/book-info/{isbn}")
    public void deleteBookInfo(@PathVariable Long isbn){
        bookService3.deleteBookInfo(isbn);
    }

    @DeleteMapping("/book-info")
    public void deleteBookInfo(@RequestBody DeleteReq deleteReq){
        List<Long> isbnList = deleteReq.getIsbnList();
        bookService3.deleteBookInfo(isbnList);
    }



    //소장도서 등록
    @PostMapping("/books")
    public void insertBook(@RequestBody BookDto bookDto){
        bookService3.insertBook(bookDto);
    }

    //소장도서 조회
    @GetMapping("/books")
    public List<BookDto> getBookList(@RequestBody(required = false) SearchDto searchDto){
        List<BookDto> bookDtoList = bookService3.getBookList(searchDto);
        return bookDtoList;
    }

    @GetMapping("/books/{seq}")
    public BookDto getBookBySeq(@PathVariable Long seq){
//        BookDto bookDto = bookService3.getBook(seq);
        // getBook안에서 할 일
        // 1. restTemplate으로 /books 요청
        // 2. 목록에서 seq에 해당하는 book 찾아서 반환

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/books")
                .encode()
                .build()
                .toUri();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<BookDto>> result = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<BookDto>>() {
        });
        List<BookDto> resultBody = result.getBody();
        BookDto bookDto = null;
        for (int i = 0; i<resultBody.size(); i++) {
            if (resultBody.get(i).getSeq().equals(seq)) {
                bookDto = resultBody.get(i);
            }
        }

        return bookDto;
    }

    @GetMapping("/books/feign/{seq}")
    public BookDto getBookBySeqWithFeign (@PathVariable Long seq) {
        List<BookDto> bookDtoList= bookFeignClient.getBooks();
        BookDto bookDto = null;
        for (int i = 0; i<bookDtoList.size(); i++) {
            if (bookDtoList.get(i).getSeq().equals(seq)) {
                bookDto = bookDtoList.get(i);
            }
        }
        return bookDto;
    }



    //소장도서 수정
    @PutMapping("/books")
    public void updateBook(@RequestBody BookDto bookDto){
        bookService3.updateBook(bookDto);
    }

    //소장도서 삭제
    @DeleteMapping("/books/{seq}")
    public void deleteBook(@PathVariable Long seq){
        bookService3.deleteBook(seq);
    }


    //카테고리 등록
    @PostMapping("/category")
    public void insertCategory(@RequestBody CategoryDto categoryDto){
        bookService3.insertCategory(categoryDto);
    }

    //카테고리 조회
    @GetMapping("/category")
    public List<CategoryDto> getCategoryList(){
        List<CategoryDto> categoryDtoList = bookService3.getCategoryList();
        return categoryDtoList;
    }

    //카테고리 수정
    @PutMapping("/category")
    public void updateCategory(@RequestBody CategoryDto categoryDto){
        bookService3.updateCategory(categoryDto);
    }

    //카테고리 삭제
    @DeleteMapping("/category/{seq}")
    public void deleteCategory(@PathVariable Long seq){
        bookService3.deleteCategory(seq);
    }




//    @Transactional
//    @Scheduled(fixedRate = 5000)
//    @SchedulerLock(name = "BookController_scheduleTest", lockAtLeastFor = "4S", lockAtMostFor = "4S")
//    public void scheduleTest() {
//        Long seq = Long.valueOf(13);
//        Long isbn = bookService3.getIsbnBySeq(seq);
//        log.info("totalRentCount>>>"+bookService3.getBookInfoByIsbn(isbn).getTotalRentCount());
//
//        bookService3.addRentCount(seq); // 스케줄러가 실행되는 메서드
//    }


}
