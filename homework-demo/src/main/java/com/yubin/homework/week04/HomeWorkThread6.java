package com.yubin.homework.week04;

import java.util.concurrent.*;

/**
 * @program: homework
 * @description:
 * @author: Yu Bin
 * @create: 2021-05-29 20:08
 **/
public class HomeWorkThread6 {

    private static ExecutorService executorService = new ThreadPoolExecutor(8, 8, 60, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(1000), new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Future<Object> future = (Future<Object>) executorService.submit(new Task(36));
        try {
            System.out.println("异步计算结果:" + future.get());
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
