package com.ojt.toyproject.book;

import com.ojt.toyproject.book.book.BookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "bookFeignClient", url = "http://localhost:8080/books")
public interface BookFeignClient {

    @GetMapping
    public List<BookDto> getBooks();

}
