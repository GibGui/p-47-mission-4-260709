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

    public List<WiseSaying> findByContentContainingDesc(String keyword){

        return wiseSayings
                .reversed()
                .stream()
                .filter( wiseSaying -> wiseSaying.getContent().contains(keyword))
                .limit(5)
                .toList();

    }
    public List<WiseSaying> findByAuthorContainingDesc(String keyword){

        return wiseSayings
                .reversed()
                .stream()
                .filter( wiseSaying -> wiseSaying.getAuthor().contains(keyword))
                .limit(5)
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
