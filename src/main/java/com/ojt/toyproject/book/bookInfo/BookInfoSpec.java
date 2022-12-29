package com.ojt.toyproject.book.bookInfo;

import com.ojt.toyproject.member.MemberEntity;
import org.springframework.data.jpa.domain.Specification;

public class BookInfoSpec {
    public static Specification<BookInfoEntity> equalIsbn(Long isbn){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isbn"), isbn);
    }
    public static Specification<BookInfoEntity> equalCategory(int category){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("category"), category);
    }
    public static Specification<BookInfoEntity> likeTitle(String title){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + title + "%");
    }
    public static Specification<BookInfoEntity> likeAuthor(String author){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("author"), "%" + author + "%");
    }
    public static Specification<BookInfoEntity> likePublisher(String publisher){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("publisher"), "%" + publisher + "%");
    }
}
