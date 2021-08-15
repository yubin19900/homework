package com.yubin.homework.mymq.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-08-02 11:31
 **/
public class MyQueue {

    private static Map<String, AtomicInteger> offSet;

    private static List<String> messageQueue;

    private Object writeLock = new Object();

    private static int writeIndex = -1;

    static {
        offSet = new ConcurrentHashMap<>();
        messageQueue = new ArrayList<>();
    }

    public void putMessage(String message) {
        synchronized (writeLock) {
            messageQueue.add(message);
            writeIndex++;
        }
    }

    public String getMessage(String consumerGroup) {
        int index = offSet.getOrDefault(consumerGroup, new AtomicInteger(0 - 1)).incrementAndGet();
        if (writeIndex == -1 || index >= messageQueue.size()) {
            return null;
        }
        return messageQueue.get(index);
    }

    public boolean isEmpty() {
        if (writeIndex == -1 || writeIndex >= messageQueue.size()) {
            return true;
        }
        return false;
    }

    public int size() {
        return writeIndex;
    }
}
