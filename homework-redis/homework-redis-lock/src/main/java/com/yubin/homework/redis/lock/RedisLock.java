package com.yubin.homework.redis.lock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-07-17 20:41
 **/
@Component
@Slf4j
public class RedisLock {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 锁等待时间
     */
    private final static int LOCK_WAIT = 5000;

    /**
     * 获得 lock.
     * 实现思路: 主要是使用了redis 的setnx命令,缓存了锁.
     * reids缓存的key是锁的key,所有的共享, value是锁的到期时间
     * 执行过程:
     * 1.通过setnx尝试设置某个key的值,成功(当前没有这个锁)则返回,成功获得锁
     * 2.锁已经存在则获取锁的到期时间,和当前时间比较,超时的话,则设置新的值
     *
     * @param key
     * @param expiresStr 当前时间+超时时间
     * @return
     * @throws InterruptedException
     */
    public boolean lock(String key, String expiresStr) throws InterruptedException {
        int timeout = LOCK_WAIT;
        while (timeout >= 0) {
            if (stringRedisTemplate.opsForValue().setIfAbsent(key, expiresStr)) {
                log.info("key:{} locked", key);
                return true;
            }
            String currentLock = stringRedisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentLock) && Long.parseLong(currentLock) < System.currentTimeMillis()) {
                //如果lockKey对应的锁已经存在，获取上一次设置的时间戳之后并重置lockKey对应的锁的时间戳
                String preLock = stringRedisTemplate.opsForValue().getAndSet(key, expiresStr);
                if (!StringUtils.isEmpty(preLock) && preLock.equals(currentLock)) {
                    log.info("key:{} locked", key);
                    return true;
                }
            }

            long sleepTime = (long) (200 * Math.random());
            timeout -= sleepTime;
            Thread.sleep(sleepTime);
        }
        return false;
    }

    public void release(String key, String expiresStr) {
        String currentValue = stringRedisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(currentValue) && currentValue.equals(expiresStr)) {
            // 删除锁状态
            stringRedisTemplate.opsForValue().getOperations().delete(key);
            log.info("key:{} release", key);
        }
    }
}
