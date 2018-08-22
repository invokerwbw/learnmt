package org.star.ch1.part3.sequence;

public class SafeSequenceTest implements Runnable {

    private SafeSequence safeSequence;

    public SafeSequenceTest(SafeSequence safeSequence) {
        this.safeSequence = safeSequence;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
//            System.out.println(Thread.currentThread().getName() + ":" + safeSequence.getNext());
            System.out.println(Thread.currentThread().getName() + ":" + safeSequence.getNext2());
        }
    }

    public static void main(String[] args) {

        SafeSequence safeSequence = new SafeSequence();

        SafeSequenceTest safeSequenceTest = new SafeSequenceTest(safeSequence);

        Thread thread1 = new Thread(safeSequenceTest);
        Thread thread2 = new Thread(safeSequenceTest);

        thread1.start();
        thread2.start();

    }

}
