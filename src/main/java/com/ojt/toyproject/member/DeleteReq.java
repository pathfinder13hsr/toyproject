package com.ojt.toyproject.member;

import lombok.Data;

import java.util.List;

@Data
public class DeleteReq {
    private List<String> idList;
}
