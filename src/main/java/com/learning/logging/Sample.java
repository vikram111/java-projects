package com.learning.logging;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sample {
    static Logger logger = Logger.getLogger("com.learning.logging");

    public static void smallestInteger(int[]a){
        Arrays.sort(a);
        int lengthOfArray = a.length;
        int highestInteger = a[lengthOfArray-1];
        int smallestInteger = a[0];
        String b = Arrays.toString(a);
        for(int i=smallestInteger; i<highestInteger; i++){
            if(b.contains(Integer.valueOf(i).toString()) || i<0)
                continue;
            else{

            }

        }
    }

    public static void main(String[] args) {
        logger.log(Level.INFO,"This is a sample new message");
        int[] a = new int[]{4,2,3,1};
        smallestInteger(a);
    }

}


