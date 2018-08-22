package org.star.ch1.part3.sequence;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class SafeSequence implements Sequence {

    private int value;

    private final AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public synchronized int getNext() {
        return value++;
    }

    public int getNext2() {
        return atomicInteger.getAndIncrement();
    }

}
