package control;

import java.io.*;
import java.net.*;

import model.Calculator;

public class ServerControl {
	private Calculator calculator;

	public ServerControl() {
		calculator = new Calculator();
	}

	public void startServidor() {
		try (ServerSocket server = new ServerSocket(5000)) {
			System.out.println("Servidor iniciado...");

			while (true) {
				try (Socket socket = server.accept();
						DataInputStream input = new DataInputStream(socket.getInputStream());
						DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {
					output.writeUTF("Bienvenido");
					double num1 = input.readDouble();
					double num2 = input.readDouble();
					String operation = input.readUTF();

					double result = operation(num1, num2, operation);
					output.writeDouble(result);
					socket.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
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
