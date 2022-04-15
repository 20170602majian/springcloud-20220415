package com.majian.fegin.service.impl;

import com.majian.fegin.service.ThreadPoolExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ThreadPoolExecutorServiceImpl implements ThreadPoolExecutorService {

    @Async("threadExecutor")
    @Override
    public void go() {
        log.info("开始执行方法，线程名称为 -> "+ Thread.currentThread().getName());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
