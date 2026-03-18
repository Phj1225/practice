package com.back;

import java.util.Scanner;

public class App {
    Scanner sc = new Scanner(System.in);
    int lastId = 0;
    WiseSaying[] wiseSayings = new WiseSaying[100];
    int lastWiseSayingIndex = -1;

    public void run(){


        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                actionWrite();
            } else if (cmd.equals("목록")) {
                actionList();
            } else if (cmd.startsWith("삭제?id")) {
                actionDelete(cmd);
            } else if (cmd.startsWith("수정?id")) {
                actionModify(cmd);
            }
        }
    }
    public void actionWrite(){
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();

        write(content,author);
        System.out.println(lastId + "번 명언이 등록되었습니다.");
    }

    public void actionList(){
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        WiseSaying[] foundedWiseSayings = findList();

        for (WiseSaying wiseSaying : foundedWiseSayings) {
            System.out.printf("%d / %s / %s\n", wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent());
        }

    }

    private void actionDelete(String cmd){ //배열을 만들었을때 길이가 100이고 삭제라는 개념이 없다 => 삭제처럼 보이게 만들면 된다. = NULL을 쓴다.
        String idStr = cmd.split("=")[1];

        int id = Integer.parseInt(idStr);

        boolean rst = delete(id);
        if(!rst){
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }
        System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
    }

    private void write(String content, String author){
        WiseSaying wiseSaying = new WiseSaying(++lastId,content,author);

        wiseSayings[++lastWiseSayingIndex] = wiseSaying;
    }

    private boolean delete(int deleteTarget){
        int foundIndex = findIndexById(deleteTarget);
        if(foundIndex == -1) return false;

        for(int i=foundIndex;i<lastWiseSayingIndex; i++){
            wiseSayings[i] = wiseSayings[i +1];
        }

        lastWiseSayingIndex--;
        return true;
    }
    private  WiseSaying[] findList(){
        WiseSaying[] foundedWiseSayings = new WiseSaying[lastWiseSayingIndex + 1];
        int foundedWiseSayingIndex = -1;

        for (int i = lastWiseSayingIndex; i >= 0; i--) {
            WiseSaying foundedWiseSaying = wiseSayings[i];
            foundedWiseSayings[++foundedWiseSayingIndex] = foundedWiseSaying;
        }

        return foundedWiseSayings;
    }

    private void actionModify(String cmd){
        String idStr = cmd.split("=")[1];
        int id = Integer.parseInt(idStr);
        WiseSaying wiseSaying = findById(id);

        if(wiseSaying == null){
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }
        System.out.print("명언(기존) : %s\n".formatted(wiseSaying.getContent()));
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가(기존) : %s\n".formatted(wiseSaying.getAuthor()));
        System.out.print("작가 : ");
        String author = sc.nextLine();
        modify(wiseSaying, content, author);
    }
    private void modify(WiseSaying wiseSaying,String content , String author){
        wiseSaying.setAuthor(author);
        wiseSaying.setContent(content);

    }
    private WiseSaying findById(int id) {

        int foundedIndex = findIndexById(id);

        if (foundedIndex == -1) {
            return null;
        }

        return wiseSayings[foundedIndex];
    }

    private int findIndexById(int id) {
        for (int i = 0; i <= lastWiseSayingIndex; i++) {
            WiseSaying foundedWiseSaying = wiseSayings[i];
            if (id == foundedWiseSaying.getId()) {
                return i;
            }
        }
        return -1;
    }
}

