package com.yubin.homework.week04;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-05-29 20:08
 **/
public class HomeWorkThread7 {
    Object object = new Object();

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        HomeWorkThread7 thread7 = new HomeWorkThread7();
        thread7.run();
        System.out.println("主线程退出，使用时间:" + (System.currentTimeMillis() - start));
    }

    private void run() {
        try {
            new Thread(new Task(36)).start();
            synchronized (object) {
                object.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class Task implements Runnable {
        int a;

        Task(int a) {
            this.a = a;
        }

        @Override
        public void run() {
            synchronized (object) {
                int sum = fibo(this.a);
                System.out.println("异步计算结果:" + sum);
                object.notify();
            }
        }

        private int fibo(int a) {
            if (a < 2)
                return 1;
            return fibo(a - 1) + fibo(a - 2);
        }
    }
}
