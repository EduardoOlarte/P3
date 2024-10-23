package test;

import model.PrintStringsThread;
import model.TwoStrings;

public class SynchronizedExample2 {

	public static void main(String[] args) {

		TwoStrings ts = new TwoStrings();

		new PrintStringsThread("Hola ", "ahi.", ts);
		new PrintStringsThread("Como ", "estas?", ts);
		new PrintStringsThread("muchas ", "gracias!", ts);
	}
}