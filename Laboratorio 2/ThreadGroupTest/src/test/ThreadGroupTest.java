package test;

import model.SimpleThread;

public class ThreadGroupTest {
	   
    public static void main (String[] args) {
       
        new SimpleThread("Boston").start();
        new SimpleThread("New York").start();
        new SimpleThread("Seoul").start();
        new SimpleThread("Bogota").start();
       
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        System.out.println("Numero de hilos en el grupo = "
                            + group.activeCount());
       
        Thread[] tarray = new Thread[10];
        int actualSize = group.enumerate(tarray);
        for (int i=0; i<actualSize;i++){
            System.out.println("Hilo " + tarray[i].getName()
                               + " en el grupo de hilo " + group.getName());
        }
       
    }
}