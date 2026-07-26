package com.back.domain.wiseSaying.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WiseSaying {
    private int id;
    private String content;
    private String author;

    public WiseSaying(String content, String author) {

        this.content = content;
        this.author = author;
    }
    public boolean isNew() {
        return id == 0;
    }


}
