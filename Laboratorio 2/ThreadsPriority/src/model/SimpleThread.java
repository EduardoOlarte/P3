package model;


public class SimpleThread extends Thread {
   
    public SimpleThread(String str) {
        super(str);
    }
   
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " " + getName()
                                 + " Prioridad = " + getPriority());
        }
        System.out.println("Terminado! " + getName());
    }
}