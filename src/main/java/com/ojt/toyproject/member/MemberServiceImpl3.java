package com.ojt.toyproject.member;

import com.ojt.toyproject.SearchDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Qualifier("Impl3_JPA")
public class MemberServiceImpl3 implements MemberService{
    private final MemberRepository memberRepository;

    public MemberServiceImpl3(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void insertMember(MemberDto memberDto) {
        if (!memberRepository.existsById(memberDto.getId())){ //DB에 동일한 ID가 없으면 insert
            memberRepository.save(memberDto.byInsert());
        }
    }

    @Override
    public List<MemberDto> getMemberList(SearchDto searchDto) {
        Page<MemberEntity> memberEntityList;
        Pageable pageable = PageRequest.of(0, 10);
        Specification<MemberEntity> spec = (root, query,criteriaBuilder) -> null;
//        try {
            if (searchDto != null && searchDto.getPage() != null && searchDto.getRecordSize() != null) {
                int page = searchDto.getPage();
                int pageSize = searchDto.getRecordSize();
                pageable = PageRequest.of(page, pageSize);
            }
//        } catch (NullPointerException e) {
//            pageable = null;
//        }

//        try {
            if (searchDto != null && searchDto.getSearchType() != null && searchDto.getKeyword() != null) {
                String searchType = searchDto.getSearchType();
                String keyword = searchDto.getKeyword();
                if (searchType.equals("id")) {
                    spec = MemberSpec.likeMemberId(keyword);
                } else if (searchType.equals("name")) {
                    spec = MemberSpec.likeName(keyword);
                } else if (searchType.equals("phone")) {
                    spec = MemberSpec.likePhone(keyword);
                } else {
                    log.info("검색조건 오류");
                }
            }
            memberEntityList = memberRepository.findAll(spec, pageable);
//        } catch (NullPointerException e) {
//            memberEntityList = memberRepository.findAll(pageable);
//        }

        List<MemberDto> memberDtoList = new ArrayList<>();
        for (MemberEntity memberEntity : memberEntityList) {
            MemberDto memberDto = MemberDto.byEntity().memberEntity(memberEntity).build();
            memberDtoList.add(memberDto);
        }
        return memberDtoList;
    }



    @Override
    public void updateMember(MemberDto memberDto) {
        MemberEntity memberEntity = memberRepository.findByMemberId(memberDto.getId());
        memberRepository.save(memberDto.byUpdate(memberEntity));
    }

    @Override
    public void deleteMember(String id) {
        if (memberRepository.existsById(id)){
            memberRepository.deleteById(id);
        }
    }

    @Override
    public void deleteMember(List<String> idList) {
        for (int i = 0; i<idList.size(); i++) {
            String id = idList.get(i);
            deleteMember(id);
        }
    }
}
