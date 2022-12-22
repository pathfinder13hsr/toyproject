package com.ojt.toyproject.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Primary
@Slf4j
@Qualifier("Impl2")
public class MemberServiceImpl2 implements MemberService{

    private final MemberMapper2 memberMapper2;

    public MemberServiceImpl2(MemberMapper2 memberMapper2) {
        this.memberMapper2 = memberMapper2;
    }

    @Override
    public void insertMember(MemberDto memberDto) {
        memberMapper2.insertMember(memberDto);
    }

    @Override
    public List<MemberDto> getMemberList() {
        List<MemberDto> memberDtoList = memberMapper2.getMemberList();
        log.info("ServiceImpl2");
        return memberDtoList;
    }

    @Override
    public void updateMember(MemberDto memberDto) {
        memberMapper2.updateMember(memberDto);
    }

    @Override
    public void deleteMember(String id) {
        memberMapper2.deleteMember(id);
    }
}
