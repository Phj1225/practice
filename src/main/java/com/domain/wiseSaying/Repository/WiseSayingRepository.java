package com.domain.wiseSaying.Repository;

import com.domain.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class WiseSayingRepository {
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

    public boolean delete(int id) {
        return wiseSayings.removeIf((wiseSaying) -> wiseSaying.getId() == id);
    }

    public WiseSaying save(WiseSaying wiseSaying) {
        if(wiseSaying.getId() == 0){
            wiseSaying.setId(++lastId);
            wiseSayings.add(wiseSaying);
        }
        return wiseSaying;
    }
}
