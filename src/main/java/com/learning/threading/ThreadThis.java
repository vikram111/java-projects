package com.learning.threading;

public class ThreadThis implements Runnable{

    private int a;
    private int b;
    private ThreadThis(int a, int b){
        this.a = a;
        this.b = b;
    }


    private void add(){
        System.out.println(this.a + this.b);
    }
    @Override
    public void run() {
        add();
    }

    public static void main(String[] args) {
        ThreadThis[] threadThis = new ThreadThis[]{new ThreadThis(1,2), new ThreadThis(3,4),new ThreadThis(5,6)};
        Thread[] threads = new Thread[threadThis.length];

        for(int i=0; i< threadThis.length; i++){
            threads[i] = new Thread(threadThis[i]);
            threads[i].setName("Adder"+i);
            System.out.println("Running thread"+threads[i].getName());
            threads[i].start();
        }
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

}
