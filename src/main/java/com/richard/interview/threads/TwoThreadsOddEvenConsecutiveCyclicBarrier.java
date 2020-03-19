package com.richard.interview.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TwoThreadsOddEvenConsecutiveCyclicBarrier {
    static final int COUNT = 100;
    private List<PrintTasks> tasks = new ArrayList<PrintTasks>();
    private ExecutorService exec = Executors.newCachedThreadPool();
    private CyclicBarrier barrier;
    public TwoThreadsOddEvenConsecutiveCyclicBarrier(int numberOfTasks, final int pause) {
        barrier = new CyclicBarrier(numberOfTasks, new Runnable() {
            public void run() {
                for(PrintTasks task : tasks) {
                    if (task.getNum() >= COUNT) {
                        exec.shutdownNow();
                        return;
                    } else {
                        task.printNum();
                    }
                }
            }
        });
        try {
            TimeUnit.MILLISECONDS.sleep(pause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < numberOfTasks; i++) {
            PrintTasks task = new PrintTasks(barrier);
            tasks.add(task);
            exec.execute(task);
        }
    }

    public static void main(String[] args) {
        new TwoThreadsOddEvenConsecutiveCyclicBarrier(2, 10);
    }

    static class PrintTasks implements Runnable {
        private static int counter = 0;
        private final int id = counter++;
        private static volatile int num = 0;

        private CyclicBarrier barrier;
        public PrintTasks(CyclicBarrier b) {
            barrier = b;
        }

        public synchronized int getNum() {
            return num;
        }

        public void run() {
            while(!Thread.interrupted()) {
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }

        public void printNum() {
            System.out.print("Thread " + id + ": ");
            for(int i = 0; i < 1; i++) {
                System.out.format("%4d", ++num);
            }
            System.out.println();
        }
    }

}
