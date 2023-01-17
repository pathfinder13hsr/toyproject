package com.ojt.toyproject.study;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter // 실습 위해 Setter 추가, Entity에는 Setter 설정하지 않음
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StudyEntity {
    private Long seq;
    private String id;
    private String name;
    private LocalDateTime signOutDateTime;
    private LocalDateTime joinDateTime;

}
