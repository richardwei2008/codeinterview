package com.richard.interview.threads;

public class TwoThreadsOddEvenConsecutive {

    private static volatile int i = 1;

    private static final int COUNT = 100;

    public static void main(String[] args) {
        System.out.println("======== Two threads print 1-100 consecutively ========");
        System.out.println("======== Thread A output odd numbers ========");
        System.out.println("======== Thread B output even numbers ========");

        final Object obj = new Object();

        Runnable runnable = new Runnable() {

            public void run() {
                synchronized (obj) {
                    for (; i <= COUNT; ) {
                        System.out.println(Thread.currentThread().getName() + " " + (i++));
                        try {
                            obj.notifyAll();
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    obj.notifyAll();
                }
            }
        };

        Thread t1 = new Thread(runnable, "Print Odd Numbers ");
        Thread t2 = new Thread(runnable, "Print Even Numbers ");
        t1.start();
        t2.start();

    }

}
