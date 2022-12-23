package com.ojt.toyproject.member;

import com.ojt.toyproject.SearchDto;
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
    public List<MemberDto> getMemberList(SearchDto searchDto) {
        List<MemberDto> memberDtoList = memberMapper.getMemberList(searchDto);
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

    //for 문으로 단건 삭제 반복
//    @Override
//    public void deleteMember(List<String> idList) {
//        for (int i=0; i<idList.size(); i++){
//            memberMapper.deleteMember(idList.get(i));
//        }
//    }

    //쿼리에서 IN 사용하여 다중 삭제
    @Override
    public void deleteMember(List<String> idList) {
        memberMapper.deleteMembers(idList);
    }
}
