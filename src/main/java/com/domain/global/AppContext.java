package com.domain.global;

import com.domain.systemcontroller.SystemController;
import com.domain.wiseSaying.controller.WiseSayingController;
import com.domain.wiseSaying.Repository.WiseSayingRepository;
import com.domain.wiseSaying.service.WiseSayingService;

public class AppContext {

    public static final WiseSayingRepository wiseSayingRepository = new WiseSayingRepository();
    public static final WiseSayingService wiseSayingService = new WiseSayingService();
    public static final WiseSayingController wiseSayingController = new WiseSayingController();
    public static final SystemController systemController = new SystemController();
}
