package com.learning.threading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceSample {

    public void threadWithNoReturn() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Adder[] adder = new Adder[]{new Adder(2, 3), new Adder(1, 2), new Adder(5, 6)};
        for (Adder add : adder) {
            executorService.submit(add);
        }

        try {
            executorService.shutdown();
            executorService.awaitTermination(60, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void executorWithReturn(){
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<Integer>[] futures = new Future[3];
        Subtracter[] subtracters = new Subtracter[]{new Subtracter(4,3), new Subtracter(2,1)};
        int i=0;
        for(Subtracter subtracter : subtracters){
            futures[i] = executorService.submit(subtracter);
            i++;
        }

        try {
            for (Future<Integer> future : futures) {
                if(future != null){
                    System.out.println(future.get());
                }
            }
            executorService.shutdown();
            executorService.awaitTermination(60, TimeUnit.SECONDS);
        }catch (ExecutionException | InterruptedException e){
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        ExecutorServiceSample executorServiceSample = new ExecutorServiceSample();
        executorServiceSample.threadWithNoReturn();
        executorServiceSample.executorWithReturn();
    }
}

class Adder implements Runnable{
    private int a;
    private int b;

    Adder(int a, int b){
        this.a = a;
        this.b = b;
    }

    void add(){
        System.out.println(this.a + this.b);
    }

    @Override
    public void run() {
        add();
    }
}

class Subtracter implements Callable<Integer>{
    private int a;
    private int b;
    Subtracter(int a, int b){
        this.a = a;
        this.b = b;
    }
    private Integer subtract(){
        return this.a - this.b;
    }
    @Override
    public Integer call() throws Exception {
        return subtract();
    }
}
