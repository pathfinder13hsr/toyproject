package com.ojt.toyproject.member;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    void insertMember(MemberDto memberDto);
    List<MemberDto> getMemberList();
    void updateMember(MemberDto memberDto);

    void deleteMember(String id);
}
