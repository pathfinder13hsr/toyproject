package com.ojt.toyproject.book;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String getBookInfoList(Model model){
        return "??";//리턴값 무슨 데이터?
    }

    //도서정보 수정
    @PutMapping("/book-info")
    public void updateBookInfo(BookInfoDto bookInfoDto){

    }

    //도서정보 삭제
    @DeleteMapping("/book-info/{isbn}")
    public void deleteBookInfo(@PathVariable Long isbn){

    }



    //소장도서 등록
    @PostMapping("/books")
    public void insertBook(BookDto bookDto){

    }

    //소장도서 조회
    @GetMapping("/books")
    public String getBookList(Model model){
        return "?"; //리턴값 무슨 데이터???
    }

    //소장도서 수정
    @PutMapping("/books")
    public void updateBook(BookDto bookDto){

    }

    //소장도서 삭제
    @DeleteMapping("/books")
    public void deleteBook(BookDto bookDto){

    }


    //카테고리 등록
    @PostMapping("/category")
    public void insertCategory(CategoryDto categoryDto){

    }

    //카테고리 조회
    @GetMapping("/category")
    public String getCategoryList(Model model){
        return "??";//리턴값??
    }

    //카테고리 수정
    @PutMapping("/category")
    public void updateCategory(CategoryDto categoryDto){

    }

    //카테고리 삭제
    @DeleteMapping("/category")
    public void deleteCategory(CategoryDto categoryDto){

    }


}
