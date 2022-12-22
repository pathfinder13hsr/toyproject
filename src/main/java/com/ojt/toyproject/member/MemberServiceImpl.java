package com.ojt.toyproject.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Qualifier("Impl1")
public class MemberServiceImpl implements MemberService {
    private final MemberMapper memberMapper;

    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public void insertMember(MemberDto memberDto) {
        memberMapper.insertMember(memberDto);
    }

    @Override
    public List<MemberDto> getMemberList() {
        List<MemberDto> memberDtoList = memberMapper.getMemberList();
        log.info("ServiceImpl1");
        return memberDtoList;
    }

    @Override
    public void updateMember(MemberDto memberDto) {
        memberMapper.updateMember(memberDto);
    }

    @Override
    public void deleteMember(String id) {
        memberMapper.deleteMember(id);
    }
}
