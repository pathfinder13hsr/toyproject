package com.ojt.toyproject.member;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //json으로 화면단에 넘김
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberService memberService2;

    public MemberController(@Qualifier("Impl1") MemberService memberService, @Qualifier("Impl2") MemberService memberService2) {
        this.memberService = memberService;
        this.memberService2 = memberService2;
    }


    //회원가입
    @PostMapping("/signup")
    public void insertMember(@RequestBody MemberDto memberDto){
        memberService.insertMember(memberDto);
    }

    //회원리스트 조회
    @GetMapping
    public List<MemberDto> getMemberList(){
        List<MemberDto> memberDtoList = memberService.getMemberList();
        return memberDtoList;
    }

    //회원정보 수정
    @PutMapping
    public void updateMember(@RequestBody MemberDto memberDto){
        memberService.updateMember(memberDto);
    }

    //회원탈퇴
    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable String id){
        memberService.deleteMember(id);
    }


}
