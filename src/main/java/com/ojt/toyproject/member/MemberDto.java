package com.ojt.toyproject.member;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDto {
    private String id;
    private String name;
    private String phone;


    @Builder(builderClassName = "byEntity", builderMethodName = "byEntity")
    public MemberDto(MemberEntity memberEntity) {
        this.id = memberEntity.getMemberId();
        this.name = memberEntity.getName();
        this.phone = memberEntity.getPhone();
    }


    public MemberEntity byInsert() {
        return MemberEntity
                .builder()
                .memberId(this.id)
                .name(this.name)
                .phone(this.phone)
                .build();
    }

    public MemberEntity byUpdate(MemberEntity memberEntity) {
        return MemberEntity
                .builder()
                .memberId(memberEntity.getMemberId())
                .name(this.name)
                .phone(this.phone)
                .build();
    }

    public MemberEntity bySelect() {
        return MemberEntity
                .builder()
                .memberId(this.id)
                .name(this.name)
                .phone(this.phone)
                .build();
    }
}
