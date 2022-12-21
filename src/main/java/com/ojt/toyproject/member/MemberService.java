package com.ojt.toyproject.member;

import java.util.List;

public interface MemberService {
    void insertMember(MemberDto memberDto);
    List<MemberDto> getMemberList();
    void updateMember(MemberDto memberDto);
    void deleteMember(String id);
}
