package com.ojt.toyproject.member;

import com.ojt.toyproject.Pagination;
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

    // repository extends JPARepository

    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public void insertMember(MemberDto memberDto) {
        memberMapper.insertMember(memberDto);
    }

    @Override
    public List<MemberDto> getMemberList(SearchDto searchDto) {
        if (searchDto.getPage() == null ){
            searchDto.setPage(1);
        }
        if (searchDto.getRecordSize() == null){
            searchDto.setRecordSize(10);
        }
        Pagination pagination = new Pagination(searchDto);
        searchDto.setPagination(pagination);
        List<MemberDto> memberDtoList = memberMapper.getMemberList(searchDto);

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

    @Override
    public MemberDto getMemberById(String id) {
        return null;
    }
}
