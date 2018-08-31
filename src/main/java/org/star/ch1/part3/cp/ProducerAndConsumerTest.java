package org.star.ch1.part3.cp;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class ProducerAndConsumerTest {

    public static void main(String[] args) {
        BlockingDeque<String> queue = new LinkedBlockingDeque<>(50);

        Producer producer1 = new Producer(queue);
        Producer producer2 = new Producer(queue);
        Producer producer3 = new Producer(queue);
        Consumer consumer1 = new Consumer(queue);
        Consumer consumer2 = new Consumer(queue);

        Thread thread1 = new Thread(producer1, "producer1");
        Thread thread2 = new Thread(producer2, "producer2");
        Thread thread3 = new Thread(producer3, "producer3");
        Thread thread4 = new Thread(consumer1, "consumer1");
        Thread thread5 = new Thread(consumer2, "consumer2");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

    }

}
