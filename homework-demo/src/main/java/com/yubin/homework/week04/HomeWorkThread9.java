package com.yubin.homework.week04;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-05-29 20:08
 **/
public class HomeWorkThread9 {
    final static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Task task = new Task(36);
        new Thread(task).start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程退出，使用时间:" + (System.currentTimeMillis() - start));
    }

    static class Task implements Runnable {
        int a;

        Task(int a) {
            this.a = a;
        }

        @Override
        public void run() {
            int sum = fibo(this.a);
            System.out.println("异步计算结果:" + sum);
            latch.countDown();
        }

        private int fibo(int a) {
            if (a < 2)
                return 1;
            return fibo(a - 1) + fibo(a - 2);
        }
    }
}
