package com.ojt.toyproject.member;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
