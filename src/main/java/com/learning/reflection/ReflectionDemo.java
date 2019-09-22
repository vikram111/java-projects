package com.learning.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionDemo {

    private String name;
    String lastName;
    private int age;

    ReflectionDemo(){

    }

    ReflectionDemo(int age){
        this.age = age;
    }
    ReflectionDemo(String lastName, int age){
        this(age);
        this.lastName = lastName;
    }

    public String getLastName(){
        System.out.println("I was called");
        return this.lastName;
    }
}

class DemoInherit extends ReflectionDemo{
    private String childName;
    protected int childAge;
    DemoInherit(){

    }
    public void getObjectInfo(Object object){
        Class<?> claszz= object.getClass();
        Field[] fields = claszz.getFields();
        Field[] declaredFields = claszz.getDeclaredFields();
        for(Field field : fields){
            System.out.println(field);
        }
        for(Field field : declaredFields){
            System.out.println("Declared fields => "+field);
        }
        Method[] methods = claszz.getMethods();
        Method[] declaredMethods = claszz.getDeclaredMethods();
        for(Method method : declaredMethods){

            System.out.println(method);
        }
        for(Method method : methods){
            if(method.getDeclaringClass() != Object.class)
                System.out.println("Method is => "+method);
        }
    }

    public void invokeMethod(Object obj){
        Class<?> clazz = obj.getClass();
        try {
            System.out.println("The class is => "+clazz);
            Method method = clazz.getMethod("getLastName");
            for(Method method1 : clazz.getDeclaredMethods()){
                System.out.println("the mthod is => "+method1);
            }
            System.out.println(method.getName());
            Object result = method.invoke(obj);
            System.out.println("The result is => "+result.toString());
        }catch (Exception e){
            System.out.println("There was an exception =>"+e.getMessage());
        }

    }

    public static void main(String[] args) {
        DemoInherit demoInherit = new DemoInherit();
        //ReflectionDemo reflectionDemo = new ReflectionDemo();
        ReflectionDemo reflectionDemo1 = new ReflectionDemo("Vik",23);
        //demoInherit.getObjectInfo(demoInherit);
        demoInherit.invokeMethod(reflectionDemo1);

    }
}