package com.learning.iterable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class PeopleIterable<Person> implements Iterable<Person>{
    List<Person> list;
    PeopleIterable(){}
    PeopleIterable(Collection<Person> personCollection){
        this.list = (ArrayList<Person>)personCollection;
    }
    @Override
    public Iterator<Person> iterator() {
        return new PersonIterator<Person>(this.list);
    }

}

class PersonIterator<Person> implements Iterator<Person>{
    List<Person> personList;
    int index=0;

    PersonIterator(){}
    PersonIterator(Collection collection){
        this.personList = (ArrayList<Person>)collection;
    }
    @Override
    public boolean hasNext() {
        return index < personList.size();
    }

    @Override
    public Person next() {
        return personList.get(index++);
    }
}

class Person{
    private String name;
    Person(){}
    Person(String name){
        this.name = name;
    }

    String getName(){
        return this.name;
    }

    void setName(String name){
        this.name = name;
    }

    public static void main(String[] args) {
        getPersons();
    }
    static void getPersons(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("vikram"));
        personList.add(new Person("veeha"));
        PeopleIterable<Person> peopleIterable = new PeopleIterable<Person>(personList);
        peopleIterable.forEach((person -> {
            System.out.println("coming from iterable");
            System.out.println(person.getName());
        }));

        PersonIterator<Person> personIterator = new PersonIterator<Person>(personList);
        for(Person person : peopleIterable){
            System.out.println("coming from foreach");
            System.out.println(person.getName());
        }
        personIterator.forEachRemaining((person)->{
            System.out.println("Coming from iterator");
            System.out.println(person.getName());
        });
    }
}
