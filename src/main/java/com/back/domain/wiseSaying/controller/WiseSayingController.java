package com.back.domain.wiseSaying.controller;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.service.WiseSayingService;
import com.back.global.AppContext;
import com.back.global.Rq;

import java.util.List;
import java.util.Scanner;

public class WiseSayingController {

    private Scanner sc;
    private WiseSayingService wiseSayingService;

    public WiseSayingController(Scanner sc) {
        this.sc = AppContext.sc;
        this.wiseSayingService = AppContext.wiseSayingService;
    }

    public void actionWrite(){
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();


        WiseSaying wiseSaying = wiseSayingService.write(content,author);

        System.out.println("%d번 명언이 등록되었습니다.".formatted(wiseSaying.getId()));
    }
    public void actionList(Rq rq){

        String keywordType = rq.getParam("keywordType", "");
        String keyword = rq.getParam("keyword", "");


        System.out.println("번호 / 작가 / 명언");
        System.out.println("-------------------");

        int page = rq.getParamAsInt("page", 1);
        int pageSize = rq.getParamAsInt("pageSize", 5);

        List<WiseSaying> wiseSayings = wiseSayingService.findListDesc( keywordType, keyword ,pageSize, page);

        wiseSayings
                .stream()
                .forEach(wiseSaying -> System.out.printf("%d / %s / %s%n", wiseSaying.getId()
                , wiseSaying.getAuthor(), wiseSaying.getContent()));

    }
    public void actionDelete(Rq rq){

        int id = rq.getParamAsInt("id" , -1);

        boolean rst = wiseSayingService.delete(id);

        if(!rst){
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
    }
    public void actionModify(Rq rq) {

        int id = rq.getParamAsInt("id", -1);

        WiseSaying wiseSaying = wiseSayingService.findByIdOrNull(id);

        if(wiseSaying == null){
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.print("명언(기존) : %s".formatted(wiseSaying.getContent()));
        String content = sc.nextLine();
        System.out.print("작가(기존) : %s".formatted(wiseSaying.getAuthor()));
        String author = sc.nextLine();

        wiseSayingService.modify(wiseSaying, content,author);

        System.out.println("%d번 명언이 수정되었습니다.".formatted(id));


    }

}
