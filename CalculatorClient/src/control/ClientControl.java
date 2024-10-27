package control;

import java.io.*;
import java.net.*;

import view.View;

public class ClientControl {
	private View view;

	public ClientControl() {
		view = new View();
	}

	public void startClient() {
		try (Socket socket = new Socket("localhost", 5000);
				DataOutputStream output = new DataOutputStream(socket.getOutputStream());
				DataInputStream input = new DataInputStream(socket.getInputStream())) {
			
			System.out.println(input.readUTF());
			double num1 = view.takeNum("Introduce el primer número: ");
			double num2 = view.takeNum("Introduce el segundo número: ");
			String operation = view.takeOperation();

			output.writeDouble(num1);
			output.writeDouble(num2);
			output.writeUTF(operation);

			double result = input.readDouble();
			view.showResult(result);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
