package com.learning.reflection.reflectionInterface;

import com.learning.example.BankAccount;
import com.learning.reflection.ReflectionDemo;

public class BankAccountWorkerImpl implements TaskWorker,Runnable {
    BankAccount bankAccount;
    @Override
    public void setTarget(Object target) {
        if(BankAccount.class.isInstance(target)){
            bankAccount = (BankAccount) target;
        }else{
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void doWork() {
        try {
            Thread thread = new Thread(this);
            thread.start();
            thread.join();
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        synchronized (bankAccount){
            bankAccount.deposit(100);
        }
    }
}

class ReflctionWork{
    public void startWork(String workerType, Object target){
        try {
            Class<?> clazz = Class.forName(workerType);
            TaskWorker taskWorker = (TaskWorker)clazz.newInstance();
            taskWorker.setTarget(target);
            taskWorker.doWork();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(100);
        ReflctionWork reflctionWork = new ReflctionWork();
        reflctionWork.startWork("com.learning.reflection.reflectionInterface.BankAccountWorkerImpl",bankAccount);
    }
}
