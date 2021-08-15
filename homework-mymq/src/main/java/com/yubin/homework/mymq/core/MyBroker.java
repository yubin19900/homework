package com.yubin.homework.mymq.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-08-02 12:17
 **/
@Component
@Slf4j
public class MyBroker {
    private Map<String, MyQueue> queueMap = new HashMap<>();

    public boolean send(String topic, String content) {
        MyQueue queue = queueMap.getOrDefault(topic, new MyQueue());
        queue.putMessage(content);
        queueMap.put(topic, queue);
        return true;
    }

    public List<String> poll(String topic, String group, int rate) {
        MyQueue queue = queueMap.get(topic);

        List<String> messages = new ArrayList<>();
        if (queue == null) {
            return messages;
        }
        log.info("queue message amount : " + queue.size());
        while (!queue.isEmpty() || rate > 0) {
            String message = queue.getMessage(group);
            if (message == null) {
                break;
            }
            messages.add(message);
            rate -= 1;
        }
        return messages;
    }
}
