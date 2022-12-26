package com.ojt.toyproject.member;

import com.ojt.toyproject.SearchDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    void insertMember(MemberDto memberDto);
    List<MemberDto> getMemberList(SearchDto searchDto);
    int countTotalMembers();

    void updateMember(MemberDto memberDto);
    void deleteMember(String id);
    void deleteMembers(List<String> idList);
}
