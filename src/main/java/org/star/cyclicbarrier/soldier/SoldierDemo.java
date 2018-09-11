package org.star.cyclicbarrier.soldier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class SoldierDemo {

    public static class Soldier implements Runnable {

        private String soliderName;

        private final CyclicBarrier cyclicBarrier;

        public Soldier(String soliderName, CyclicBarrier cyclicBarrier) {
            this.soliderName = soliderName;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {

            try {
                //等待所有士兵到齐
                cyclicBarrier.await();
                doWork();
                //等待所有士兵完成工作
                cyclicBarrier.await();
            } catch (InterruptedException e) {//在等待过程中,线程被中断
                e.printStackTrace();
            } catch (BrokenBarrierException e) {//表示当前CyclicBarrier已经损坏.系统无法等到所有线程到齐了.
                e.printStackTrace();
            }

        }

        void doWork() {
            try {
                Thread.sleep(Math.abs(new Random().nextInt() % 10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(soliderName + "：任务完成");
        }

    }

    public static class BarrierRun implements Runnable {

        boolean flag;
        int num;

        public BarrierRun(boolean flag, int num) {
            this.flag = flag;
            this.num = num;
        }

        @Override
        public void run() {

            if (flag) {
                System.out.println("司令：【士兵" + num + "个，任务完成！】");
            } else {
                System.out.println("司令：【士兵" + num + "个，集合完毕！】");
                flag = true;
            }

        }
    }

    public static void main(String[] args) {
        final int N = 10;
        Thread[] allSoldier = new Thread[N];
        boolean flag = false;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N, new BarrierRun(flag, N));
        //设置屏障点,主要为了执行这个方法
        System.out.println("集合队伍！");
        for (int i = 0; i < N; i++) {
            System.out.println("士兵" + i + "报道！");
            allSoldier[i] = new Thread(new Soldier("士兵" + i, cyclicBarrier));
            allSoldier[i].start();
        }
    }

}
