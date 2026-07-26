package com.back.domain.wiseSaying.service;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;
import com.back.global.AppContext;

import java.util.List;

public class WiseSayingService {


    private WiseSayingRepository wiseSayingRepository;

    public WiseSayingService() {

        this.wiseSayingRepository = AppContext.wiseSayingRepository;
    }

    public WiseSaying write(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(content, author);
        wiseSayingRepository.save(wiseSaying);

        return wiseSaying;

    }

    public List<WiseSaying> findListDesc(String keywordType, String keyword, int pageSize, int page) {


        if (keywordType.equals("content")) {
            return wiseSayingRepository.findByContentContainingDesc(keyword, pageSize, page);

        } else {
            return wiseSayingRepository.findByAuthorContainingDesc(keyword, pageSize, page);

        }


    }


    public boolean delete(int id) {
        return wiseSayingRepository.delete(id);

    }

    public void modify(WiseSaying wiseSaying, String content, String author) {

        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);

        wiseSayingRepository.save(wiseSaying);


    }

    public WiseSaying findByIdOrNull(int id) {
        return wiseSayingRepository.findByIdOrNull(id);

    }


}
