package com.ojt.toyproject.book.category;

import com.ojt.toyproject.book.book.BookEntity;
import com.ojt.toyproject.book.bookInfo.BookInfoDto;
import com.ojt.toyproject.book.bookInfo.BookInfoEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CategoryDto {
    private Long seq;
    private String categoryName;

    private BookInfoDto bookInfoDto;

    @Builder(builderClassName = "byEntity", builderMethodName = "byEntity")
    public CategoryDto(CategotyEntity categotyEntity) {
        this.seq = categotyEntity.getSeq();
        this.categoryName = categotyEntity.getCategoryName();
    }



    public CategotyEntity byInsert() {
        return CategotyEntity
                .builder()
                .seq(this.seq)
                .categoryName(this.categoryName)
                .build();
    }


    public CategotyEntity byUpdate(CategotyEntity categotyEntity) {
        return CategotyEntity
                .builder()
                .seq(categotyEntity.getSeq())
                .categoryName(this.categoryName)
                .build();
    }
}
