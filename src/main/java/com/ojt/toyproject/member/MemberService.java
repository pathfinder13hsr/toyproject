package com.ojt.toyproject.member;

import com.ojt.toyproject.SearchDto;

import java.util.List;


public interface MemberService {
    void insertMember(MemberDto memberDto);
    List<MemberDto> getMemberList(SearchDto searchDto);
    void updateMember(MemberDto memberDto);
    void deleteMember(String id);
    void deleteMember(List<String> idList);
}
