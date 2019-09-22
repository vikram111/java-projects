package com.learning.serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Account implements Serializable {
    private static final long serialVersionUID = -7293585200191905194L;
    String id;
    int balance;
    char lastTxType;
    int lastTxAmount;
    String accountType;

    public void deposit(int amount){
        this.balance+=amount;
        this.lastTxType= 'd';
        this.lastTxAmount = amount;
    }
    public int getBalance(){
        return this.balance;
    }
    public void saveAccount(Account account, String fileName){
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))){
            objectOutputStream.writeObject(account);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public Account loadAccount(String fileName){
        Account account =null;
        try(ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)))){
            account = (Account) objectInputStream.readObject();
        }catch (IOException | ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
        return account;
    }

    @Override
    public String toString(){
        return String.format("The account %s has a balance of %d and accountType is %s and the last transaction type was %s",id,balance,accountType,lastTxType);
    }

    public void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException{
        Account account = null;
        ObjectInputStream.GetField field =objectInputStream.readFields();
        String id = (String) field.get("id","unknown");
    }

    public void writeObject(ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException{
        objectOutputStream.writeObject(this);
    }


    public static void main(String[] args) {
        final String FILENAME = "account.dat";
        Account account = new Account();
        account.deposit(100);
        //account.saveAccount(account,FILENAME);
        Account loadedAccount = account.loadAccount(FILENAME);
        System.out.println("The balance is => "+loadedAccount.getBalance());
        System.out.println(loadedAccount.toString());
    }
}


