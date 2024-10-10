package uptc.edu.co.test;

import java.util.Scanner;

import uptc.edu.co.model.Checker;
import uptc.edu.co.structure.MyQueue;
import uptc.edu.co.structure.MyStack;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Checker checker = new Checker();

        System.out.println("Cantidad de Binarios a sumar?");
        int numCount = scanner.nextInt();
        scanner.nextLine(); 

        MyQueue<Integer> resultQueue = null;

        for (int i = 1; i <= numCount; i++) {
            System.out.println("Ingrese binario " + i + ":");
            String binaryNumber = scanner.nextLine();

            MyStack<Integer> stack = new MyStack<>();
            for (char c : binaryNumber.toCharArray()) {
                stack.push(Character.getNumericValue(c));
            }

            if (i == 1) {
                resultQueue = checker.sumBinary(stack, new MyStack<>()); 
            } else {
                resultQueue = checker.sumWithQueue(stack, resultQueue);
            }
        }

        String finalResult = checker.result(resultQueue);
        System.out.println("El resultado es " + finalResult);

        scanner.close();
    }
}
