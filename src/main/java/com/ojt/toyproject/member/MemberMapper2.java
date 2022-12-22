package com.ojt.toyproject.member;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemberMapper2 {
    @Insert("INSERT INTO member VALUES (#{id}, #{name}, #{phone})")
    void insertMember(MemberDto memberDto);

    @Select("SELECT id, name, phone FROM member")
    List<MemberDto> getMemberList();

    @Update("UPDATE member SET name = #{name}, phone = #{phone} WHERE id = #{id}")
    void updateMember(MemberDto memberDto);
    @Delete("DELETE FROM member WHERE id=#{id}")
    void deleteMember(String id);
}
