package com.ojt.toyproject.book.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    boolean existsByIsbn(Long isbn);
    @Query(value = "SELECT COUNT(*) FROM book WHERE is_in_stock = 'Y' AND  isbn = :isbn")
    int getInStockCountByIsbn(@Param(value = "isbn") Long isbn);

    @Query(value = "SELECT COUNT(*) FROM book WHERE is_in_stock = 'N' AND  isbn = :isbn")
    int getRentCountByIsbn(@Param(value = "isbn") Long isbn);
    int countByIsbn(Long isbn);

    @Query(value = "SELECT MAX(book.stock_num) FROM book WHERE isbn = :isbn", nativeQuery = true) // 에러나서 nativeQuery = true 추가하니까 동작은 되는데, 위에 두개는 잘 되다가 왜 얘만 안되는지 모르겠음
    int getMaxStockNumByIsbn(@Param(value = "isbn") Long isbn);

    BookEntity findBySeq(Long seq);

}
