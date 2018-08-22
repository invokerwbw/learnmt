package org.star.ch1.part3.sequence;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class UnsafeSequence implements Sequence {

    private int value;

    @Override
    public int getNext() {
        return value++;
    }

}
