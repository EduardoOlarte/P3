package model;

import java.util.concurrent.atomic.AtomicInteger;

public class CalculatorThread implements Runnable {
	private int start;
	private int end;
	private AtomicInteger totalPrimes;

	public CalculatorThread(int start, int end, AtomicInteger totalPrimes) {
		this.start = start;
		this.end = end;
		this.totalPrimes = totalPrimes;
	}

	@Override
	public void run() {
		calculatorPrimeNumbers();
	}

	public void calculatorPrimeNumbers() {
		int count = 0;
		for (int i = start; i < end; i++) {
			if (isPrime(i)) {
				count++;
			}
		}
		totalPrimes.addAndGet(count);
	}

	public boolean isPrime(int numero) {
		if (numero <= 1) {
			return false;
		}
		for (int i = 2; i <= Math.sqrt(numero); i++) {
			if (numero % i == 0) {
				return false;
			}
		}
		return true;
	}
}