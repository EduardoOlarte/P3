package uptc.edu.co.model;

import java.util.ArrayList;
import java.util.List;

import uptc.edu.co.structure.MyQueue;

public class Checker {

	private static final int BASE = 6;

	public Checker(List<Integer> numbers) {
		if (numbers == null || numbers.isEmpty()) {
			return;
		}

		int max = getMax(numbers);
		int exp = 1;

		while (max / exp > 0) {
			countingSort(numbers, exp);
			exp *= BASE;
		}
	}

	public int getMax(List<Integer> numbers) {
		int max = numbers.get(0);
		for (int num : numbers) {
			if (num > max) {
				max = num;
			}
		}
		return max;
	}

	public void countingSort(List<Integer> numbers, int exp) {
		MyQueue<Integer>[] queues = new MyQueue[BASE];
		for (int i = 0; i < BASE; i++) {
			queues[i] = new MyQueue<>();
		}

		for (int num : numbers) {
			int digit = (num / exp) % BASE;
			queues[digit].push(num);
		}

		int index = 0;
		for (int i = 0; i < BASE; i++) {
			while (!queues[i].isEmpty()) {
				numbers.set(index++, queues[i].poll());
			}
		}
	}
}
