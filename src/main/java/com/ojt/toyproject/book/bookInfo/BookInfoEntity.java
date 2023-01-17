package com.ojt.toyproject.book.bookInfo;

import com.ojt.toyproject.book.category.CategoryEntity;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@ToString
@Entity(name = "book_info")
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookInfoEntity {
    @Id
    private Long isbn;
//    private int category;
    private String title;
    private String author;
    private String publisher;
    private Long totalRentCount;

    @ManyToOne
    @JoinColumn(name = "category")
    private CategoryEntity categoryEntity;



}
