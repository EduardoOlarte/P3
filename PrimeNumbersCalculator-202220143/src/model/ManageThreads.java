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
		this.totalPrimes = new AtomicInteger(0);
	}

	public void run() {
		caculatorNumThreads();
		createThreads();
		waitThreads();
	}

	public void caculatorNumThreads() {
		int pcThreads = Runtime.getRuntime().availableProcessors();
		if (numThreads > pcThreads) {
			numThreads = pcThreads;
		}

	}

	public void createThreads() {
		threads = new Thread[numThreads];
		int range = limit / numThreads;
		for (int i = 0; i < numThreads; i++) {
			int threadStart = i * range;
			int threadEnd = range * (i + 1);
			threads[i] = new Thread(new CalculatorThread(threadStart, threadEnd, totalPrimes));
			threads[i].start();
		}
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