package com.ojt.toyproject.member;

import com.ojt.toyproject.SearchDto;
import com.ojt.toyproject.rent.RentDto;
import com.ojt.toyproject.rent.RentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController //json으로 화면단에 넘김
@RequestMapping("/members")
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final MemberService memberService2;
    private final MemberService memberService3;
    private final RentService rentService;

    public MemberController(@Qualifier("Impl1") MemberService memberService, @Qualifier("Impl2") MemberService memberService2, @Qualifier("Impl3_JPA") MemberService memberService3, @Qualifier("Impl1") RentService rentService) {
        this.memberService = memberService;
        this.memberService2 = memberService2;
        this.memberService3 = memberService3;
        this.rentService = rentService;
    }


    //회원가입
    @PostMapping("/signup")
    public void insertMember(@RequestBody MemberDto memberDto){
        memberService3.insertMember(memberDto);
    }

    //회원리스트 조회
    @GetMapping
    public List<MemberDto> getMemberList(@RequestBody(required = false) SearchDto searchDto){
        List<MemberDto> memberDtoList = memberService3.getMemberList(searchDto);
        return memberDtoList;
    }

    //회원정보 수정
    @PutMapping
    public void updateMember(@RequestBody MemberDto memberDto){
        memberService3.updateMember(memberDto);
    }

    //회원삭제
    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable String id){
        memberService3.deleteMember(id);
    }

    //회원삭제 - 여러건
    @DeleteMapping
    public void deleteMembers(@RequestBody DeleteReq deleteReq){
        List<String> idList = deleteReq.getIdList();
        memberService3.deleteMember(idList);
    }


    @GetMapping("/{id}/mypage")
    public Map<String, List<RentDto>> getRentListById(@PathVariable String id){
        Map<String, List<RentDto>> rentListMap = rentService.getRentListById(id);
        return rentListMap;
    }


}
