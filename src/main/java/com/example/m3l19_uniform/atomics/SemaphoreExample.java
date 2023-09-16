package com.example.m3l19_uniform.atomics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(100);

        List<Thread> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(
                    () -> {
                        try {
                            semaphore.acquire();
                        } catch (InterruptedException e){
                            throw new RuntimeException(e);
                        }
                        for (int j = 0; j < 3; j++) {
                            System.out.println(Thread.currentThread().getName());
                            try {
                                Thread.sleep(1000);
                                semaphore.release();
                            }catch (InterruptedException e){
                                throw new RuntimeException(e);
                            }
                        }
                    }
            );
            list.add(thread);
        }
        for (Thread thread : list) {
            thread.start();
        }

    }
}
