package com.learning.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileTools {
    public void readFile(String readFile,String writeFile){
        String line=null;
        try(BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(readFile));
            BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(writeFile))){
            while((line=bufferedReader.readLine())!=null){
                bufferedWriter.write(line);
            }
        }catch (IOException e){
            System.out.println("There was an exception"+e);
        }
    }

    public static void main(String[] args) {
        FileTools fileTools = new FileTools();
        fileTools.readFile("/Users/vikramthakur/Documents/java-workspace/collectionslearning/sample-read.txt", "/Users/vikramthakur/Documents/java-workspace/collectionslearning/sample-write.txt");
    }

}
