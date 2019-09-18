package com.learning.list;

import java.util.Comparator;
import java.util.TreeSet;

public class SortingLists {

    public static void main(String[] args) {
        MyClass myClass = new MyClass("1111","abc");
        MyClass myClass1 = new MyClass("2222","def");
        MyClass myClass2 = new MyClass("0000","adsa");

        TreeSet<MyClass> treeSet = new TreeSet<MyClass>();
        treeSet.add(myClass);
        treeSet.add(myClass1);
        treeSet.add(myClass2);

        treeSet.forEach(element -> System.out.println(element.getLabel()));

        TreeSet<MyClass> treeSet1 = new TreeSet<MyClass>(new NewComparison());
            treeSet1.add(myClass);
            treeSet1.add(myClass1);
            treeSet1.add(myClass2);
            treeSet1.forEach(element -> System.out.println(element.getValue()));
    }

}

class MyClass implements Comparable{
    String label,value;

    public MyClass(String label, String value){
        this.label = label;
        this.value = value;
    }

    public String getLabel(){
        return this.label;
    }

    public String getValue(){
        return this.value;
    }

    public boolean equals(Object o){
        MyClass myClass = (MyClass) o;
        return myClass.getLabel().equalsIgnoreCase(this.label);
    }

    @Override
    public int compareTo(Object o) {
        MyClass myClass = (MyClass) o;
        return this.label.compareToIgnoreCase(myClass.getLabel());
    }
}


class NewComparison implements Comparator<MyClass>{


    @Override
    public int compare(MyClass o1, MyClass o2) {
        return o1.getValue().compareToIgnoreCase(o2.getValue());
    }

    @Override
    public Comparator<MyClass> reversed() {
        return null;
    }
}
