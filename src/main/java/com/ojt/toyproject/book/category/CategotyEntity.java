package com.ojt.toyproject.book.category;

import com.ojt.toyproject.book.bookInfo.BookInfoEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString
@Entity(name = "category")
@NoArgsConstructor
public class CategotyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    private String categoryName;


    @Builder
    public CategotyEntity(Long seq, String categoryName) {
        this.seq = seq;
        this.categoryName = categoryName;
    }

}
