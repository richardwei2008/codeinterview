package com.richard.interview.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class TwoThreadsOddEvenConsecutiveLatch {

    private static AtomicInteger counter = new AtomicInteger(1);

    private static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("======== Two threads print 1-100 consecutively ========");
        System.out.println("======== Thread A output odd numbers ========");
        System.out.println("======== Thread B output even numbers ========");

        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (counter.intValue() < 100) {
                    if (counter.intValue() % 2 == 1) {
                        System.out.println("Odd Threads: " + counter.intValue());
                        counter.incrementAndGet();
                    }
                    countDownLatch.countDown();
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                while (counter.intValue() <= 100) {
                    if (counter.intValue() % 2 == 0) {
                        System.out.println("Even Threads: " + counter.intValue());
                        counter.incrementAndGet();
                    }
                    countDownLatch.countDown();
                }
            }
        };

        t1.start();
        t2.start();
        countDownLatch.await();

    }

}
