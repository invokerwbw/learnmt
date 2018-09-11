package org.star.countdownlatch.rocket;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RocketDemo implements Runnable {

    static final CountDownLatch end = new CountDownLatch(10);
    static final RocketDemo demo = new RocketDemo();

    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(10) * 1000);
            System.out.println("check complete..." + end.getCount());
            end.countDown();//完成 可以减1
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            exec.submit(demo);
        }
        try {
            end.await();//主线程等待所有10个线程完成任务 才能继续执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Fire!");
        exec.shutdown();
    }
}
