package com.learning.annotationWorker;

import com.learning.annotation.ProcessedBy;
import com.learning.annotation.WorkHandler;
import com.learning.example.BankAccount;
import com.learning.reflection.reflectionInterface.TaskWorker;
import com.sun.corba.se.spi.orbutil.threadpool.Work;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@WorkHandler
public class AnnotationWorker  implements TaskWorker,Runnable {
    BankAccount bankAccount;
    @Override
    public void setTarget(Object target) {
        if(BankAccount.class.isInstance(target)){
            bankAccount = (BankAccount) target;
        }
    }

    @Override
    public void doWork() {
        try {
            Thread thread = new Thread(this);
            thread.start();
            thread.join();
        }catch (InterruptedException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void run() {
        synchronized (bankAccount){
            bankAccount.deposit(100);
            bankAccount.deposit(100);
            bankAccount.withdraw(50);
        }
    }
}

class EnableWorker{
    public void startWork(Object targetType){
        Class<?> clazz = targetType.getClass();
        ProcessedBy processedBy = clazz.getAnnotation(ProcessedBy.class);
        Class<?> classWorker = processedBy.value();
        try {
            TaskWorker taskWorker = (TaskWorker) classWorker.newInstance();
            WorkHandler workHandler = classWorker.getAnnotation(WorkHandler.class);
            if(workHandler!=null && workHandler.value()){

                ExecutorService executorService = Executors.newFixedThreadPool(5);
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Using threadpool");
                        taskWorker.setTarget(targetType);
                        taskWorker.doWork();
                    }
                });
            }else{
                System.out.println("Not using thread pool");
                taskWorker.setTarget(targetType);
                taskWorker.doWork();
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        EnableWorker enableWorker = new EnableWorker();
        BankAccount bankAccount = new BankAccount(100);
        enableWorker.startWork(bankAccount);
    }
}
