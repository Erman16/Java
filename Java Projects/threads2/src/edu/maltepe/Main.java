package edu.maltepe;

public class Main {

    public static void main(String[] args) {
        RunnableDemo r1 = new RunnableDemo( "Thread-1");


        RunnableDemo r2 = new RunnableDemo( "Thread-2");


        RunnableDemo r3 = new RunnableDemo( "Thread-3");

        RunnableDemo r4 = new RunnableDemo( "Thread-4");
        r1.start();
        r2.start();
        r4.start();
        r3.start();


    }
}
