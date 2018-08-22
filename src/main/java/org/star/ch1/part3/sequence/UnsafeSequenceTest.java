package org.star.ch1.part3.sequence;

public class SequenceTest implements Runnable {

    private UnsafeSequence unsafeSequence;

    public SequenceTest(UnsafeSequence unsafeSequence) {
        this.unsafeSequence = unsafeSequence;
    }

    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + unsafeSequence.getNext());
        }

    }

    public static void main(String[] args) {

        UnsafeSequence unsafeSequence = new UnsafeSequence();

        SequenceTest sequenceTest = new SequenceTest(unsafeSequence);

        Thread thread1 = new Thread(sequenceTest);
        Thread thread2 = new Thread(sequenceTest);

        thread1.start();
        thread2.start();

    }


}
