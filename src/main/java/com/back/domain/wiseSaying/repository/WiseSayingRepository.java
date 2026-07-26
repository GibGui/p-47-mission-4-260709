package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {


    private List<WiseSaying> wiseSayings = new ArrayList<>();
    private int lastId = 0;

    public WiseSaying save(WiseSaying wiseSaying) {
        if (wiseSaying.isNew()) {
            wiseSaying.setId(++lastId);
            wiseSayings.add(wiseSaying);
        }
        return wiseSaying;

    }


    public List<WiseSaying> findListDesc() {
        return wiseSayings.reversed();

    }

    public List<WiseSaying> findByContentContaining(String keyword){

        return wiseSayings.stream()
                .filter( wiseSaying -> wiseSaying.getContent().contains(keyword))
                .toList();

    }
    public List<WiseSaying> findByAuthorContaining(String keyword){

        return wiseSayings.stream()
                .filter( wiseSaying -> wiseSaying.getAuthor().contains(keyword))
                .toList();

    }


    public boolean delete(int id) {
        return wiseSayings.removeIf( w -> w.getId() == id);
    }

    public WiseSaying findByIdOrNull(int id) {

        return wiseSayings.stream()
                .filter( w-> w.getId() == id)
                .findFirst()
                .orElse(null);


    }


}
