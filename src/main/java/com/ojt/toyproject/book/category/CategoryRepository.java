package com.ojt.toyproject.book.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategotyEntity, Long> {
    List<CategotyEntity> findAll();
    CategotyEntity findBySeq(Long seq);
}
