package com.learning.synchronised;

import com.learning.example.BankAccount;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Worker implements Runnable {

    BankAccount bankAccount;

    Worker(BankAccount bankAccount){
        this.bankAccount = bankAccount;
    }

    @Override
    public void run() {
        for(int i =0; i < 10; i++) {
            synchronized (bankAccount) {
                bankAccount.deposit(10);
            }
        }
    }

    @Override
    public int hashCode(){
        return 1;
    }
    @Override
    public boolean equals(Object a) {
        if(a instanceof Worker)
        return true;

        return false;
    }
}

class MultiWorker{
    static Logger logger = Logger.getLogger("com.learning.example");
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(10);
        Worker worker = new Worker(bankAccount);
        Worker worker1 = new Worker(bankAccount);
        logger.log(Level.INFO,worker.toString()+worker1.toString());
        Class<?> clazz = worker.getClass();
        logger.log(Level.INFO,"The class name is =>"+clazz.getSimpleName());
        if(worker.equals(worker1)){
            logger.log(Level.INFO,worker.toString()+worker1.toString());
            logger.log(Level.INFO,"They are equal");
        }else{
            logger.log(Level.INFO,"They are not equal");
        }
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i =0 ; i< 5; i++) {
            executorService.submit(worker);
        }
        try{
            executorService.shutdown();
            executorService.awaitTermination(60, TimeUnit.SECONDS);
        }catch ( InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}