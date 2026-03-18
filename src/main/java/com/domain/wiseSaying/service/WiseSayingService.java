package com.domain.wiseSaying.service;

import com.domain.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class WiseSayingService {
    private List<WiseSaying> wiseSayings = new ArrayList<>();
    private int lastId = 0;

    public WiseSaying findById(int id) {

        int foundedIndex = findIndexById(id);

        if (foundedIndex == -1) {
            return null;
        }

        return wiseSayings.get(foundedIndex);
    }

    private int findIndexById(int id) {
        return IntStream
                .range(0, wiseSayings.size())
                .filter((i) -> wiseSayings.get(i).getId() == id)
                .findFirst()
                .orElse(-1);
    }

    public List<WiseSaying> findList() {

        List<WiseSaying> foundedWiseSayings = new ArrayList<>();

        for (WiseSaying wiseSaying : wiseSayings.reversed()) {
            foundedWiseSayings.add(wiseSaying);
        }

        return foundedWiseSayings;
    }

    public void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);
    }

    public boolean delete(int deleteTarget) {
        return wiseSayings.removeIf((wiseSaying) -> wiseSaying.getId() == deleteTarget);
    }

    public WiseSaying write(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(++lastId, content, author);
        wiseSayings.add(wiseSaying);

        return wiseSaying;
    }
}
