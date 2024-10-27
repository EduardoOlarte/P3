package control;

import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.AtomicInteger;

import model.Calculator;
import model.ServerThread;

public class ServerControl {
	private Calculator calculator;
	private AtomicInteger numThreads;

	public ServerControl() {
		calculator = new Calculator();
		numThreads = new AtomicInteger(12);
	}

	public void startServidor() {

		try (ServerSocket server = new ServerSocket(5000)) {
			System.out.println("Servidor iniciado...");

			while (true) {
				Socket socket = server.accept();
				DataOutputStream output = new DataOutputStream(socket.getOutputStream());
				if (availableThreads()) {
					System.out.println("Cliente conectado: " + socket.getInetAddress());

					ServerThread handler = new ServerThread(socket, calculator, numThreads);
					Thread clientThread = new Thread(handler);
					clientThread.start();
					numThreads.addAndGet(1);
				}else {
					output.writeUTF("Servidor lleno, intente mas tarde");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean availableThreads() {
		int pcThreads = Runtime.getRuntime().availableProcessors();
		if (numThreads.get() > pcThreads || numThreads.get() == pcThreads) {
			return false;
		} else {
			return true;
		}

	}

}
