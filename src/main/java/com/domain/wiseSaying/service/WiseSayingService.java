package com.domain.wiseSaying.service;

import com.domain.wiseSaying.entity.WiseSaying;

import com.domain.wiseSaying.Repository.WiseSayingRepository;
import java.util.List;

public class WiseSayingService {
    WiseSayingRepository wiseSayingRepository = new WiseSayingRepository();

    public WiseSaying findById(int id) {

        return wiseSayingRepository.findById(id);
    }

    public List<WiseSaying> findList() {

        return wiseSayingRepository.findList();
    }

    public void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);
    }

    public boolean delete(int deleteTarget) {
        return wiseSayingRepository.delete(deleteTarget);
    }

    public WiseSaying write(String content, String author) {
        return wiseSayingRepository.save(content,author);
    }
}
