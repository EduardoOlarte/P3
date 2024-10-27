package view;

import java.util.Scanner;

public class View {
    private Scanner scanner;

    public View() {
        scanner = new Scanner(System.in);
    }

    public double takeNum(String message) {
        System.out.println(message);
        return scanner.nextDouble();
    }

    public String takeOperation() {
        System.out.println("operación (+, -, *, /): ");
        return scanner.next().toUpperCase();
    }

    public void showResult(double result) {
        System.out.println("Resultado: " + result);
    }
}

