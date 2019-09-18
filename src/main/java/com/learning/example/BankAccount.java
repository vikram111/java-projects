package com.learning.example;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BankAccount {
    static Logger logger = Logger.getLogger("com.learning.example");
    private int deposit;

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
}
