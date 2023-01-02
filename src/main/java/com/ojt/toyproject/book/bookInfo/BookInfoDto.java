package com.ojt.toyproject.book.bookInfo;

import com.ojt.toyproject.member.MemberEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor //MemberDto는 기본 생성자 없이도 잘 됐는데 왜 얘는 없으면 에러??
public class BookInfoDto {
    private Long isbn;
    private int category;
    private String title;
    private String author;
    private String publisher;
    private Long totalRentCount;


    private int totalStockCount;
    private int rentCount;
    private int inStockCount;
    private int bookingCount;


    @Builder(builderClassName = "byEntity", builderMethodName = "byEntity")
    public BookInfoDto(BookInfoEntity bookInfoEntity) {
        this.isbn = bookInfoEntity.getIsbn();
        this.category = bookInfoEntity.getCategory();
        this.title = bookInfoEntity.getTitle();
        this.author = bookInfoEntity.getAuthor();
        this.publisher = bookInfoEntity.getPublisher();
        this.totalRentCount = bookInfoEntity.getTotalRentCount();
    }


    public BookInfoEntity byInsert() {
        return BookInfoEntity
                .builder()
                .isbn(this.isbn)
                .category(this.category)
                .title(this.title)
                .author(this.author)
                .publisher(this.publisher)
                .totalRentCount(Long.valueOf(0))
                .build();
    }


    public BookInfoEntity byUpdate(BookInfoEntity bookInfoEntity) {
        return BookInfoEntity
                .builder()
                .isbn(bookInfoEntity.getIsbn())
                .category(this.category)
                .title(this.title)
                .author(this.author)
                .publisher(this.publisher)
                .totalRentCount(bookInfoEntity.getTotalRentCount())
                .build();
    }

}
