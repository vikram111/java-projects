package com.learning.reflection;

import com.learning.example.BankAccount;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class BankAccountWorker implements Runnable {
    BankAccount bankAccount;
    BankAccountWorker(){}
    public BankAccountWorker(BankAccount bankAccount){
        this.bankAccount = bankAccount;
    }

    public void run(){
        synchronized (bankAccount){
            bankAccount.deposit(100);
            System.out.println("The deposit is => "+bankAccount.getDeposit());
        }

    }

    public void doWork(){
        try {
            Thread thread = new Thread(this);
            thread.start();
            thread.join();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}

class DispatchWorker{
    void startWork(String workerType, Object worker){
        try{
            Class<?> accountWorker = Class.forName(workerType);
            Class<?> targetType =worker.getClass();
            Constructor constructor = accountWorker.getConstructor(targetType);
            Object bankAccountWorker = constructor.newInstance(worker);
            Method method = accountWorker.getMethod("doWork");
            method.invoke(bankAccountWorker);
        }catch (Exception e){
            System.out.println("There was an exception while dispatching work => "+e);
        }

    }

    public static void main(String[] args) {
        DispatchWorker dispatchWorker = new DispatchWorker();
        BankAccount bankAccount = new BankAccount(100);
        dispatchWorker.startWork("com.learning.reflection.BankAccountWorker",bankAccount);
    }
}
