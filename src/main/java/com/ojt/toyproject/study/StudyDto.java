package com.ojt.toyproject.study;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class StudyDto {
    private Long seq;
    private String userId;
    private String userName;
    private LocalDateTime createDateTime;
    private LocalDateTime deleteDateTime;
    private List<String> classList;
}
