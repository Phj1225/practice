package com.domain;

import com.domain.systemcontroller.SystemController;
import com.domain.wiseSaying.controller.WiseSayingController;
import com.domain.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class App {
    private Scanner sc = new Scanner(System.in);
    private SystemController systemController = new SystemController();
    private WiseSayingController wiseSayingController = new WiseSayingController();

    public void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            Rq rq = new Rq(cmd); // cmd 분석 객체
            String action = rq.getAction();

            if (action.equals("종료")) {
                systemController.exit();
                break;
            } else if (action.equals("등록")) {
                wiseSayingController.actionWrite();
            } else if (action.equals("목록")) {
                wiseSayingController.actionList();
            } else if (action.startsWith("삭제")) {
                wiseSayingController.actionDelete(rq);
            } else if (action.startsWith("수정")) {
                wiseSayingController.actionModify(rq);
            }
        }
    }
}

