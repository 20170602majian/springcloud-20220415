package com.majian.javabasic.lock;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock(true);
        reentrantLock.lock();
        Map map = new HashMap(11);
        map.put("","");
        map.remove("");
        List list = new ArrayList(30);
        Set set = new HashSet(20);

        reentrantLock.unlock();
    }
}
