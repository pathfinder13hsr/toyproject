package com.ojt.toyproject.book.bookInfo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@ToString
@Entity(name = "book_info")
@DynamicUpdate
@NoArgsConstructor
public class BookInfoEntity {
    @Id
    private Long isbn;
    private int category;
    private String title;
    private String author;
    private String publisher;
    private Long totalRentCount;

    @Builder
    public BookInfoEntity (Long isbn, int category, String title, String author, String publisher, Long totalRentCount) {
        this.isbn = isbn;
        this.category = category;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.totalRentCount = totalRentCount;
    }

}
