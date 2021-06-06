package com.yubin.homework.week04;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-05-29 20:08
 **/
public class HomeWorkThread8 {
    private static AtomicInteger num = new AtomicInteger();

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        new Thread(new Task(36)).start();
        while (true) {
            if (num.get() == 1) {
                System.out.println("主线程退出，使用时间:" + (System.currentTimeMillis() - start));
                break;
            }
        }
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
            num.incrementAndGet();
        }

        private int fibo(int a) {
            if (a < 2)
                return 1;
            return fibo(a - 1) + fibo(a - 2);
        }
    }
}
