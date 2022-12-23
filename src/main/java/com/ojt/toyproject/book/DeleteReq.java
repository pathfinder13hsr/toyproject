package com.ojt.toyproject.book;

import lombok.Data;

import java.util.List;

@Data
public class DeleteReq {
    private List<Long> isbnList;
}
