package com.learning.list;

import java.util.ArrayList;

public class ListExamples {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();

        list.add("vikram");
        list.add(1);
        list.add(2.0);

        for(Object o : list){
            if(o instanceof String){
                System.out.println("String is => "+o);
            }else{
                System.out.println("Other data type is => "+o);
            }
        }

        list.forEach(element -> System.out.println(element));
        list.removeIf(element -> {return element instanceof String;});
        list.forEach(element -> System.out.println(element));
    }
}
