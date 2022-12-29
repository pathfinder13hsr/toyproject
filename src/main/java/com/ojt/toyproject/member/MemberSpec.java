package com.ojt.toyproject.member;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class MemberSpec {
    public static Specification<MemberEntity> likeMemberId(String memberId){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("memberId"), "%" + memberId + "%");
    }
    public static Specification<MemberEntity> likeName(String name){
        return new Specification<MemberEntity>() {
            @Override
            public Predicate toPredicate(Root<MemberEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("name"), "%" + name + "%");
            }
        };
    }

    public static Specification<MemberEntity> likePhone(String phone){
        return new Specification<MemberEntity>() {
            @Override
            public Predicate toPredicate(Root<MemberEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("phone"), "%" + phone + "%");
            }
        };
    }


}
