package test;

import model.PrintStringsThread;

public class UnsynchronizedExample {

	public static void main(String[] args) {
		new PrintStringsThread("Hola ", "alli.");
		new PrintStringsThread("como estas ", "tu?");
		new PrintStringsThread("muchas ", "Gracias!");
	}

}