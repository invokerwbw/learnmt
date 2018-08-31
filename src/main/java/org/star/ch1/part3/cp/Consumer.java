package org.star.ch1.part3.cp;


import java.util.Random;
import java.util.concurrent.BlockingDeque;

public class Consumer implements Runnable {

    private volatile boolean isRunning = true;

    private BlockingDeque<String> queue;
    private static final int DEFAULT_RANGE_FOR_SLEEP = 1000;

    public Consumer(BlockingDeque<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Random random = new Random();
        String threadName = Thread.currentThread().getName();
        System.out.println("启动消费者线程：" + threadName);

        try {
            while (isRunning) {
                System.out.println(threadName + "正从队列获取数据...");

                String data = queue.take();
                if (data != null) {
                    System.out.println(threadName + "消费数据：" + data + "，队列长度：" + queue.size());
                    Thread.sleep(random.nextInt(DEFAULT_RANGE_FOR_SLEEP));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(threadName + "退出消费者线程！");
        }

    }
}
