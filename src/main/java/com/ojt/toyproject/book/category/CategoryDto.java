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

//    private BookInfoDto bookInfoDto;

    @Builder(builderClassName = "byEntity", builderMethodName = "byEntity")
    public CategoryDto(CategoryEntity categotyEntity) {
        this.seq = categotyEntity.getSeq();
        this.categoryName = categotyEntity.getCategoryName();
    }



    public CategoryEntity byInsert() {
        return CategoryEntity
                .builder()
                .seq(this.seq)
                .categoryName(this.categoryName)
                .build();
    }


    public CategoryEntity byUpdate(CategoryEntity categotyEntity) {
        return CategoryEntity
                .builder()
                .seq(categotyEntity.getSeq())
                .categoryName(this.categoryName)
                .build();
    }
}
