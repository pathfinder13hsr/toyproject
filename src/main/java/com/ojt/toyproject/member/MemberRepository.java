package com.ojt.toyproject.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository

public interface MemberRepository extends JpaRepository<MemberEntity, String> , JpaSpecificationExecutor<MemberEntity> {

    MemberEntity findByMemberId(String id);
    boolean existsById(String id);

//    Page<MemberEntity> findAll(Pageable pageable);

    Page<MemberEntity> findAll(Specification<MemberEntity> spec, Pageable pageable);



}
