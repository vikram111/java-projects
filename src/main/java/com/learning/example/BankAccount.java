package com.learning.example;

import com.learning.annotation.ProcessedBy;
import com.learning.annotationWorker.AnnotationWorker;

import java.util.logging.Level;
import java.util.logging.Logger;

@ProcessedBy(AnnotationWorker.class)
public class BankAccount {
    static Logger logger = Logger.getLogger("com.learning.example");
    private int deposit;
    public BankAccount(){}
    public BankAccount(int deposit){
        this.deposit = deposit;
    }

    public void deposit(int amount){
        this.deposit+=amount;
        logger.log(Level.INFO, "The deposit is => "+deposit);
    }

    public int getDeposit(){
        return this.deposit;
    }

    public void withdraw(int withdrawal){
        this.deposit-=withdrawal;
        logger.log(Level.INFO, "The balane is => "+deposit);
    }
}
