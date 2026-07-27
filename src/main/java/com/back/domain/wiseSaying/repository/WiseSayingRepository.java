package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.dto.PageDto;
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

    public PageDto findByAuthorContainingDesc(String keyword, int pageSize, int page){

        List<WiseSaying> filtered =  wiseSayings
                .reversed()
                .stream()
                .filter(wiseSaying -> wiseSaying.getAuthor().contains(keyword))
                .toList();



        return pageOf(filtered, page,pageSize);
    }

    public PageDto findByContentContainingDesc(String keyword, int pageSize, int page){

        List<WiseSaying> filtered = wiseSayings
                .reversed()
                .stream()
                .filter(wiseSaying -> wiseSaying.getContent().contains(keyword))
                .toList();

        return pageOf(filtered, page, pageSize);

    }

    public PageDto pageOf(List<WiseSaying> filteredContent, int pageNo, int pageSize){

        List<WiseSaying> content = filteredContent
                .stream()
                .skip((pageNo-1) * pageSize)
                .limit(pageSize)
                .toList();

        int totalItems = filteredContent.size();

        return new PageDto(pageNo, pageSize, totalItems, content);
    }




    public boolean delete(int id) {
        return wiseSayings.removeIf(w -> w.getId() == id);
    }

    public WiseSaying findByIdOrNull(int id) {

        return wiseSayings.stream()
                .filter(w -> w.getId() == id)
                .findFirst()
                .orElse(null);


    }


}
