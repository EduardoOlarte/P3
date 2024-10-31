package model;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientHandler implements Runnable {
	private Socket clientSocket;
	private Dictionary dictionary;

	public ClientHandler(Socket clientSocket, Dictionary dictionary) {
		this.clientSocket = clientSocket;
		this.dictionary = dictionary;
	}

	@Override
	public void run() {
		try (BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true)) {

			String request;
			while ((request = input.readLine()) != null) {
				String response = processRequest(request);
				output.println(response);
			}

		} catch (IOException e) {
			System.out.println("Cliente desconectado: " + clientSocket.getInetAddress());
		} finally {
			try {
				InetAddress clientAddress = clientSocket.getInetAddress();
				clientSocket.close();
				System.out.println("Cliente desconectado: "+ clientAddress);
				clientAddress=null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private String processRequest(String request) {
		String[] parts = request.split(";");
		String action = parts[0];

		switch (action) {
		case "translate":
			return translateWord(parts[1], Integer.parseInt(parts[2]));
		case "add":
			return addWord(parts[1], parts[2], Integer.parseInt(parts[3]));
		case "info":
			return showDictionariesInfo();
		default:
			return "Acción desconocida";
		}
	}

	private String translateWord(String word, int type) {
		String translation = dictionary.translate(word, type);
		return (translation != null) ? translation : "La palabra no se encontró en el diccionario.";
	}

	private String addWord(String original, String translation, int type) {
		boolean success = dictionary.addWord(original, translation, type);
		return success ? "Palabra agregada correctamente." : "Error al agregar la palabra.";
	}

	private String showDictionariesInfo() {
		StringBuilder info = new StringBuilder();
		info.append("Tamaño del diccionario ").append("En-Es").append(": ").append(dictionary.sizeDictionaries(0))
				.append("\n");
		info.append("Tamaño del diccionario ").append("En-Fr").append(": ").append(dictionary.sizeDictionaries(1))
				.append("\n");

		return info.toString() + "END";
	}

}
