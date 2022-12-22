package com.ojt.toyproject.book;



import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    private final BookMapper bookMapper;

    public BookServiceImpl(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    //도서정보 CRUD
    @Override
    public void insertBookInfo(BookInfoDto bookInfoDto) {
        bookMapper.insertBookInfo(bookInfoDto);
    }

    @Override
    public List<BookInfoDto> getBookInfoList() {
        List<BookInfoDto> bookInfoDtoList = bookMapper.getBookInfoList();
        return bookInfoDtoList;
    }

    @Override
    public void updateBookInfo(BookInfoDto bookInfoDto) {
        bookMapper.updateBookInfo(bookInfoDto);
    }

    @Override
    public void deleteBookInfo(Long isbn) {
        bookMapper.deleteBookInfo(isbn);
    }


    //소장도서 CRUD
    @Override
    public void insertBook(BookDto bookDto) {
        bookMapper.insertBook(bookDto);
    }

    @Override
    public List<BookDto> getBookList() {
        List<BookDto> bookDtoList = bookMapper.getBookList();
        return bookDtoList;
    }

    @Override
    public void updateBook(BookDto bookDto) {
        bookMapper.updateBook(bookDto);
    }

    @Override
    public void deleteBook(Long seq) {
        bookMapper.deleteBook(seq);
    }

    //카테고리 CRUD
    @Override
    public void insertCategory(CategoryDto categoryDto) {
        bookMapper.insertCategory(categoryDto);
    }

    @Override
    public List<CategoryDto> getCategoryList() {
        List<CategoryDto> categoryDtoList = bookMapper.getCategoryList();
        return categoryDtoList;
    }

    @Override
    public void updateCategory(CategoryDto categoryDto) {
        bookMapper.updateCategory(categoryDto);
    }

    @Override
    public void deleteCategory(Long seq) {
        bookMapper.deleteCategory(seq);
    }

    @Override
    public void addRentCount(Long bookSeq) {
        bookMapper.addRentCount(bookSeq);
    }

    @Override
    public void changeBookStatus(Long seq) {
        bookMapper.changeBookStatus(seq);
    }
}
