package model;

import java.util.concurrent.atomic.AtomicInteger;

public class ManageThreads {
	private int limit;
	private int numThreads;
	private AtomicInteger totalPrimes;
	private Thread[] threads;

	public ManageThreads(int limit, int numThreads) {
		this.limit = limit;
		this.numThreads = numThreads;
		this.threads = new Thread[numThreads];
		this.totalPrimes = new AtomicInteger(0);
	}

	public void run() {

		int range = limit / numThreads;

		for (int i = 0; i < numThreads; i++) {
			int threadStart = i * range;
			int threadEnd = range * (i + 1);
			threads[i] = new Thread(new CalculatorThread(threadStart, threadEnd, totalPrimes));
			threads[i].start();
		}
		waitThreads();
	}

	public void waitThreads() {
		for (int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public AtomicInteger getTotalPrimes() {
		return totalPrimes;
	}

}
