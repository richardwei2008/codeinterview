package com.richard.interview.threads;

import java.util.concurrent.Semaphore;

public class TwoThreadsOddEvenConsecutiveSemaphore {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(1);

        SemaphorePrintEven semaphorePrintEven = new SemaphorePrintEven(semaphore);
        Thread t2 = new Thread(semaphorePrintEven);
        t2.start();

        SemaphorePrintOdd semaphorePrintOdd = new SemaphorePrintOdd(semaphore);
        Thread t1 = new Thread(semaphorePrintOdd);
        t1.start();


    }


    static class SemaphorePrintOdd implements Runnable {

        int i = 1;
        private Semaphore semaphore;

        public SemaphorePrintOdd(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        public void run() {
            try {
                semaphore.acquire();
                while (true && i <= 100) {
                    if (i % 2 == 0 ) {
                        System.out.println("Even Threads: " + i);
                        semaphore.release();
                    }
                    i++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class SemaphorePrintEven implements Runnable {

        int i = 1;
        private Semaphore semaphore;

        public SemaphorePrintEven(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        public void run() {
            try {
                semaphore.acquire();
                while (true && i <= 100) {
                    if (i % 2 == 1) {
                        System.out.println("Odd Thread: " + i);
                        semaphore.release();
                    }
                    i++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
