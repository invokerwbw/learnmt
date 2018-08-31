package org.star.ch1.part3.cp;

import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {

    private volatile boolean isRunning = true;

    private BlockingDeque<String> queue;

    private static AtomicInteger conut = new AtomicInteger();

    private static final int DEFAULT_RANGE_FOR_SLEEP = 1000;

    public Producer(BlockingDeque<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        String data = null;
        Random random = new Random();
        String threadName = Thread.currentThread().getName();
        System.out.println("启动生产者线程：" + threadName);

        try {
            while (isRunning) {
                System.out.println(threadName + "正在生产数据...");
                Thread.sleep(random.nextInt(DEFAULT_RANGE_FOR_SLEEP));

                data = "data：" + conut.incrementAndGet();
                queue.put(data);
                System.out.println(threadName + "将数据：" + data + "放入队列...队列长度：" + queue.size());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(threadName + "退出生产者线程！");
        }

    }

    public void stop() {
        isRunning = false;
    }

}
