package test;

import model.ManageThreads;

public class Test {
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		int limit = 500000;
		ManageThreads primeNumbers = new ManageThreads(limit);
		primeNumbers.run();

		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;

		System.out.println("Total de números primos: " + primeNumbers.getTotalPrimes());
		System.out.println("Tiempo de ejecución: " + duration + " milisegundos");
	}
}
