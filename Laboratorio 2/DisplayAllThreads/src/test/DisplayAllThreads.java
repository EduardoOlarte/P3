package test;

import model.SimpleThread;

public class DisplayAllThreads {
   
    public static void main(String[] args) {
       
		new SimpleThread("Boston").start();
        new SimpleThread("New York").start();
        new SimpleThread("Seoul").start();
        new SimpleThread("Bogota").start();
       
        Thread[] tarray = findAllThreads();
       
        for (int i=0; i<tarray.length;i++){
            System.out.println("Hilo " + tarray[i].getName()
            + " en grupo de hilo " + tarray[i].getThreadGroup().getName());
        }
       
    }
   
    public static Thread[] findAllThreads() {
        ThreadGroup group = Thread.currentThread().getThreadGroup();
       
        ThreadGroup topGroup = group;
       
        while (group != null) {
            topGroup = group;
            group = group.getParent();
        }
       
        int estimatedSize = topGroup.activeCount() * 2;
        Thread[] slackList = new Thread[estimatedSize];
       
        int actualSize = topGroup.enumerate(slackList);
       
        Thread[] list = new Thread[actualSize];
        System.arraycopy(slackList, 0, list, 0, actualSize);
       
        return list;
    }
}