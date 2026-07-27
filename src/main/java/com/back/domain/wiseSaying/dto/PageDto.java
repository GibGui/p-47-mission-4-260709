package com.back.domain.wiseSaying.dto;

import com.back.domain.wiseSaying.entity.WiseSaying;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor

public class PageDto {
    private int page; // 현재 페이지
    private int pageSize; // 한 페이지에 보여줄 명언 갯수
    private int totalItem; // 전체 명언 갯수
    private List<WiseSaying> content; // 한 페이지를 담은 리스트

}
