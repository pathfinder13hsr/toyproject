package com.ojt.toyproject.member;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

//Entity에서는 Setter ㄴㄴ
@Getter
@ToString
@Entity(name = "member")
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberEntity {
    @Id
    @Column(name="id")
    private String memberId; //Repository에서 findById를 쓸 수 없었음, findeByMemberId로 작성하니까 작동 됨, Entity에서 memberId로 바꿈
    private String name;
    private String phone;

}
