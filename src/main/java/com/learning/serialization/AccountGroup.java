package com.learning.serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountGroup implements Serializable {
    static Logger logger = Logger.getLogger("com.learning.serializable");
    private Map<String, SavingsAccount> accountMap = new HashMap<>();
    private transient int totalBalance;

    public int getTotatBalance(){
        return totalBalance;
    }

    public void addAccount(SavingsAccount bankAccount){
        accountMap.put(bankAccount.getId(),bankAccount);
        this.totalBalance+=bankAccount.getBalance();
    }

    public void getAccountBalance(){
        for(SavingsAccount savingsAccount : this.accountMap.values()){
            logger.log(Level.INFO,savingsAccount.toString());
        }
    }

    public void saveAccounts(String fileName){
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))){
            objectOutputStream.writeObject(this);
        }catch (IOException ex){
            logger.log(Level.SEVERE,ex.getMessage());
        }
    }

    public AccountGroup loadAccounts(String fileName){
        AccountGroup accountGroup = null;
        try(ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)))){
            accountGroup = (AccountGroup) objectInputStream.readObject();
        }catch (IOException | ClassNotFoundException ex){
            logger.log(Level.SEVERE,ex.getMessage());
        }
        return accountGroup;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder(40);
        for(SavingsAccount savingsAccount : this.accountMap.values()){
            stringBuilder.append("The account is => "+savingsAccount+"\n");
        }
        stringBuilder.append("The total balance is => "+getTotatBalance());
        return stringBuilder.toString();
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException{
        objectInputStream.defaultReadObject();
        for(SavingsAccount savingsAccount : accountMap.values()){
            logger.log(Level.ALL,"Using the custom readObject");
            totalBalance+=savingsAccount.getBalance();
        }
    }

    public static void main(String[] args) {
        final String FILENAME = "account-group.dat";
        SavingsAccount savingsAccount = new SavingsAccount("1111",100);
        SavingsAccount savingsAccount1 = new SavingsAccount("2222",200);
        AccountGroup accountGroup = new AccountGroup();
        accountGroup.addAccount(savingsAccount);
        accountGroup.addAccount(savingsAccount1);
        accountGroup.saveAccounts(FILENAME);
        logger.log(Level.INFO,accountGroup.loadAccounts(FILENAME).toString());
    }

}

class SavingsAccount implements Serializable{
    static Logger logger = Logger.getLogger("com.learning.serializable");
    private String id;
    private int balance;

    SavingsAccount(String id, int balance){
        this.id = id;
        this.balance = balance;
    }

    public int getBalance(){
        return  this.balance;
    }
    public String getId(){
        return this.id;
    }
    public void saveAccount(SavingsAccount savingsAccount, String fileName){
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))){
            objectOutputStream.writeObject(savingsAccount);
        }catch (IOException ex){
            logger.log(Level.SEVERE,ex.getMessage());
        }
    }

    public SavingsAccount loadAccount(String fileName){
        SavingsAccount savingsAccount = null;
        try(ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)))){
            savingsAccount =(SavingsAccount) objectInputStream.readObject();
        }catch (IOException | ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
        return savingsAccount;
    }

    @Override
    public String toString(){
        return String.format("The account id %s has balance %d",id, balance);
    }

}
