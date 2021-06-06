package com.yubin.homework.week04;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-05-29 20:08
 **/
public class HomeWorkThread3 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Task task = new Task(36);
        try {
            FutureTask<Integer> result = new FutureTask<>(task);
            new Thread(result).start();
            Integer sum = result.get();
            System.out.println("异步计算结果:" + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("主线程退出，使用时间:" + (System.currentTimeMillis() - start));
    }

    static class Task implements Callable {
        int a;

        Task(int a) {
            this.a = a;
        }

        private int fibo(int a) {
            if (a < 2)
                return 1;
            return fibo(a - 1) + fibo(a - 2);
        }

        @Override
        public Object call() throws Exception {
            int sum = fibo(this.a);
            return sum;
        }
    }
}
