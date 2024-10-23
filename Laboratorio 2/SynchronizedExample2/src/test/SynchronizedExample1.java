package test;

import model.PrintStringsThread;

public class SynchronizedExample1 {
	   
    public static void main(String[] args) {
        new PrintStringsThread("Hola ", "alli.");
        new PrintStringsThread("Como estas ", "tu?");
        new PrintStringsThread("Muchas ", "gracias!");
    }
   
}