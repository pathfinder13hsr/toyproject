package com.ojt.toyproject.book.bookInfo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookInfoRepository extends JpaRepository<BookInfoEntity, Long>, JpaSpecificationExecutor<BookInfoEntity> {

    boolean existsByIsbn(Long isbn);
    BookInfoEntity findByIsbn(Long isbn);
//    Page<BookInfoEntity> findAll(Pageable pageable);
    Page<BookInfoEntity> findAll(Specification<BookInfoEntity> spec, Pageable pageable);

}
