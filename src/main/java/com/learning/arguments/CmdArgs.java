package com.learning.arguments;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class CmdArgs {

    public static void main(String[] args) {
        if(args.length == 0 ){
            System.out.println("No filename passed");
            return;
        }
        String fileName = args[0];
        if(Files.exists(Paths.get(fileName))){
            readFile(fileName);
        }
        System.out.println("-----------------------------");
        writeProperties();
        readProperties();
        System.out.println("------------------------------");
        writePropertiesXml();
        readFromXml();
        readPropsResource();
        compareMessage();
    }

    public static void readFile(String fileName){
        try(BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fileName))){
            String line = null;
            while((line = bufferedReader.readLine())!=null){
                System.out.println(line);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void writeProperties(){
        Properties properties = new Properties();
        properties.setProperty("name","Vikram");
        properties.setProperty("lastName","Thakur");

        try(BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get("myapp.properties"))){
            properties.store(bufferedWriter,"first written properties");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void readProperties(){
        Properties properties = new Properties();
        try(BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("myapp.properties"))){
            properties.load(bufferedReader);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        System.out.println(properties.getProperty("name"));
    }

    private static void writePropertiesXml(){
        Properties properties = new Properties();
        properties.setProperty("name","vikram");
        try(OutputStream outputStream = Files.newOutputStream(Paths.get("props.xml"))){
            properties.storeToXML(outputStream,"this is a comment");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static void readFromXml(){
        Properties properties = new Properties();
        try(InputStream inputStream = Files.newInputStream(Paths.get("props.xml"))){
            properties.loadFromXML(inputStream);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        System.out.println(properties.getProperty("name"));
    }

    private static void readPropsResource(){
        Properties properties = new Properties();
        try{
            InputStream inputStream = CmdArgs.class.getResourceAsStream("application.xml");
            properties.loadFromXML(inputStream);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        Properties props = new Properties(properties);
        System.out.println("Name is => "+ properties.getProperty("name"));
        System.out.println("Game is => "+ properties.getProperty("game"));

    }

    private static void compareMessage(){
        String message ="SP0002";
        if(message.matches("SP000[2-6]")){
            System.out.println("passed");
        }else{
            System.out.println("fails");
        }
    }

    Iterable<CmdArgs> iterable = new Iterable<CmdArgs>() {
        List<CmdArgs> cmdArgs = new ArrayList<CmdArgs>();
        @Override
        public Iterator<CmdArgs> iterator() {
            return cmdArgs.iterator();
        }
    };

    Iterator<CmdArgs> iterator = new Iterator<CmdArgs>() {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public CmdArgs next() {
            return null;
        }
    };
}
