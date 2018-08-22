package org.star.ch1.part3.sequence;

public class UnsafeSequenceTest implements Runnable {

    private UnsafeSequence unsafeSequence;

    public UnsafeSequenceTest(UnsafeSequence unsafeSequence) {
        this.unsafeSequence = unsafeSequence;
    }

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + unsafeSequence.getNext());
        }

    }

    public static void main(String[] args) {

        UnsafeSequence unsafeSequence = new UnsafeSequence();

        UnsafeSequenceTest unsafeSequenceTest = new UnsafeSequenceTest(unsafeSequence);

        Thread thread1 = new Thread(unsafeSequenceTest);
        Thread thread2 = new Thread(unsafeSequenceTest);

        thread1.start();
        thread2.start();

    }


}
