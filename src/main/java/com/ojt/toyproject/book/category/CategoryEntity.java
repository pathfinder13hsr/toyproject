package com.ojt.toyproject.book.category;

import lombok.*;

import javax.persistence.*;

@Getter
@ToString
@Entity(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    private String categoryName;




}
