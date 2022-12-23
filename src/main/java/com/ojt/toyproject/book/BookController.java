package com.ojt.toyproject.book;

import com.ojt.toyproject.SearchDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //도서정보 : book_info, 소장도서 : book, 도서카테고리 : category ->세 테이블 CRUD

    //도서정보 등록
    @PostMapping("/book-info")
    public void insertBookInfo(@RequestBody BookInfoDto bookInfoDto){
        bookService.insertBookInfo(bookInfoDto);
    }

    //도서정보 조회
    @GetMapping("/book-info")
    public List<BookInfoDto> getBookInfoList(@RequestBody(required = false) SearchDto searchDto){
        List<BookInfoDto> bookInfoDtoList = bookService.getBookInfoList(searchDto);
        return bookInfoDtoList;
    }

    //도서정보 수정
    @PutMapping("/book-info")
    public void updateBookInfo(@RequestBody BookInfoDto bookInfoDto){
        bookService.updateBookInfo(bookInfoDto);
    }

    //도서정보 삭제
    @DeleteMapping("/book-info/{isbn}")
    public void deleteBookInfo(@PathVariable Long isbn){
        bookService.deleteBookInfo(isbn);
    }

    @DeleteMapping("/book-info")
    public void deleteBookInfo(@RequestBody DeleteReq deleteReq){
        List<Long> isbnList = deleteReq.getIsbnList();
        bookService.deleteBookInfo(isbnList);
    }



    //소장도서 등록
    @PostMapping("/books")
    public void insertBook(@RequestBody BookDto bookDto){
        bookService.insertBook(bookDto);
    }

    //소장도서 조회
    @GetMapping("/books")
    public List<BookDto> getBookList(){
        List<BookDto> bookDtoList = bookService.getBookList();
        return bookDtoList;
    }

    //소장도서 수정
    @PutMapping("/books")
    public void updateBook(@RequestBody BookDto bookDto){
        bookService.updateBook(bookDto);
    }

    //소장도서 삭제
    @DeleteMapping("/books/{seq}")
    public void deleteBook(@PathVariable Long seq){
        bookService.deleteBook(seq);
    }


    //카테고리 등록
    @PostMapping("/category")
    public void insertCategory(@RequestBody CategoryDto categoryDto){
        bookService.insertCategory(categoryDto);
    }

    //카테고리 조회
    @GetMapping("/category")
    public List<CategoryDto> getCategoryList(){
        List<CategoryDto> categoryDtoList = bookService.getCategoryList();
        return categoryDtoList;
    }

    //카테고리 수정
    @PutMapping("/category")
    public void updateCategory(@RequestBody CategoryDto categoryDto){
        bookService.updateCategory(categoryDto);
    }

    //카테고리 삭제
    @DeleteMapping("/category/{seq}")
    public void deleteCategory(@PathVariable Long seq){
        bookService.deleteCategory(seq);
    }


}
