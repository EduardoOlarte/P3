package uptc.edu.co.test;

import java.util.ArrayList;
import java.util.List;

import uptc.edu.co.model.Checker;

public class Test {

	public static void main(String[] args) {
		List<Integer> numbers = new ArrayList<>();
		numbers.add(345);
		numbers.add(123);
		numbers.add(234);
		numbers.add(432);
		numbers.add(543);
		numbers.add(231);
		numbers.add(111);
		numbers.add(555);

		System.out.println("antes: " + numbers);

		Checker checker = new Checker(numbers);

		System.out.println("despues: " + numbers);
	}
}
