package model;

import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerThread implements Runnable {
	private Socket socket;
	private Calculator calculator;
	private AtomicInteger numThreads;

	public ServerThread(Socket socket, Calculator calculator, AtomicInteger numThreads) {
		this.socket = socket;
		this.calculator = calculator;
		this.numThreads = numThreads;
	}

	@Override
	public void run() {
		try (DataInputStream input = new DataInputStream(socket.getInputStream());
				DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {
			
			output.writeUTF("Bienvenido");
			double num1 = input.readDouble();
			double num2 = input.readDouble();
			String operation = input.readUTF();

			double result = operation(num1, num2, operation);

			output.writeDouble(result);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
				numThreads.decrementAndGet();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private double operation(double num1, double num2, String operation) {
		switch (operation) {
		case "+":
			return calculator.sum(num1, num2);
		case "-":
			return calculator.subtract(num1, num2);
		case "*":
			return calculator.multiply(num1, num2);
		case "/":
			return calculator.divide(num1, num2);
		default:
			throw new IllegalArgumentException("Operación no soportada");
		}
	}
}
