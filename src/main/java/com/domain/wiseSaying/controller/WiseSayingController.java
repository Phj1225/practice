package com.domain.wiseSaying.controller;

import com.domain.Rq;
import com.domain.wiseSaying.entity.WiseSaying;
import com.domain.wiseSaying.service.WiseSayingService;


import java.util.List;
import java.util.Scanner;



public class WiseSayingController {
    private Scanner sc = new Scanner(System.in);
    private WiseSayingService wiseSayingService = new WiseSayingService();


    //등록
    public void actionWrite() {
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();

        WiseSaying wiseSaying= wiseSayingService.write(content,author);
        System.out.println(wiseSaying.getId()+ "번 명언이 등록되었습니다.");
    }


    //삭제
    public void actionDelete(Rq rq) {

        int id = rq.getParamAsInt("id", -1);

        if (id == -1) {
            System.out.println("id를 제대로 입력해주세요.");
            return;
        }

        boolean rst = wiseSayingService.delete(id);

        if (!rst) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
    }


    //목록
    public void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        List<WiseSaying> foundedWiseSayings = wiseSayingService.findList();

        for (WiseSaying wiseSaying : foundedWiseSayings) {
            System.out.printf("%d / %s / %s\n", wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent());
        }
    }

    //수정
    public void actionModify(Rq rq) {
        int id = rq.getParamAsInt("id", -1);

        if (id == -1) {
            System.out.println("id를 제대로 입력해주세요.");
            return;
        }
        WiseSaying wiseSaying = wiseSayingService.findById(id);

        if (wiseSaying == null) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.print("명언(기존) : %s\n".formatted(wiseSaying.getContent()));
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가(기존) : %s\n".formatted(wiseSaying.getAuthor()));
        System.out.print("작가 : ");
        String author = sc.nextLine();

        wiseSayingService.modify(wiseSaying,content,author);

    }
}
