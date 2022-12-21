package com.ojt.toyproject.book;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    private final BookMapper bookMapper;

    public BookServiceImpl(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public void insertBookInfo(BookInfoDto bookInfoDto) {
        bookMapper.insertBookInfo(bookInfoDto);
    }

    @Override
    public List<BookInfoDto> getBookInfoList() {
        return null;
    }

    @Override
    public void updateBookInfo(BookInfoDto bookInfoDto) {

    }

    @Override
    public void deleteBookInfo(int isbn) {

    }

    @Override
    public void insertBook(BookDto bookDto) {

    }

    @Override
    public List<BookDto> getBookList() {
        return null;
    }

    @Override
    public void updateBook(BookDto bookDto) {

    }

    @Override
    public void deleteBook(int isbn) {

    }

    @Override
    public void insertCategory(CategoryDto categoryDto) {

    }

    @Override
    public List<CategoryDto> getCategoryList() {
        return null;
    }

    @Override
    public void updateCategory(CategoryDto categoryDto) {

    }

    @Override
    public void deleteCategory(Long seq) {

    }
}
