package com.yubin.homework.week04;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-05-29 20:08
 **/
public class HomeWorkThread2 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int a = 36;
        Thread t = new Thread(() -> {
            int sum = fibo(a);
            System.out.println("异步计算结果:" + sum);
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程退出，使用时间:" + (System.currentTimeMillis() - start));
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}
