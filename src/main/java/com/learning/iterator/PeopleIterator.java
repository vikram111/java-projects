package com.learning.iterator;

import org.json.JSONArray;
import org.json.JSONException;
import java.util.Iterator;

public class PeopleIterator {
    private String name;
    private int age;
    public PeopleIterator(){
    }

    PeopleIterator(String name, int age){
        this.name = name;
        this.age = age;
    }
    String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
    int getAge(){
        return this.age;
    }
    public void setAge(int age){
        this.age = age;
    }
    Iterator iterator = new Iterator() {
        PeopleIterator[] iterators;
        int index=0;
        @Override
        public boolean hasNext() {
            return index < iterators.length;
        }

        @Override
        public Object next() {
            return iterators[index++];
        }
    };


    public static void main(String[] args) {
        PeopleIterator[] peopleIterators = {
                new PeopleIterator("Vikram", 10),
                new PeopleIterator("Neha",9)
        };
    }
}


class MyArray extends JSONArray implements Iterator<PeopleIterator>{
    private int index=0;
    @Override
    public boolean hasNext() {
        return index < this.length();
    }


    @Override
    public PeopleIterator next() {
        try {
            return (PeopleIterator)this.get(index++);
        }catch (JSONException e){
            e.getCause();
            return null;
        }
    }

    public static void main(String[] args) {

        MyArray myArray = new MyArray();

        myArray.put(new PeopleIterator("vikram",10));
        myArray.put(new PeopleIterator("Neha",10));

        myArray.forEachRemaining((element) -> {
            System.out.println(element.getName());
            System.out.println(element.getAge());
        });


    }

}
