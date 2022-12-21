package com.ojt.toyproject.member;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //json으로 화면단에 넘김
@RequestMapping("/members")
public class MemberController {
    private final MemberServiceImpl memberServiceImpl;

    public MemberController(MemberServiceImpl memberServiceImpl) {
        this.memberServiceImpl = memberServiceImpl;
    }


    //회원가입
    @PostMapping("/signup")
    public void insertMember(@RequestBody MemberDto memberDto){
        memberServiceImpl.insertMember(memberDto);
    }

    //회원리스트 조회
    @GetMapping
    public List<MemberDto> getMemberList(){
        List<MemberDto> memberDtoList = memberServiceImpl.getMemberList();
        return memberDtoList;
    }

    //회원정보 수정
    @PutMapping
    public void updateMember(@RequestBody MemberDto memberDto){
        memberServiceImpl.updateMember(memberDto);
    }

    //회원탈퇴
    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable String id){
        memberServiceImpl.deleteMember(id);
    }


}
