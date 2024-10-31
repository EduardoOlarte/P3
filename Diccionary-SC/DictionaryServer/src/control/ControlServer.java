package control;

import model.ClientHandler;
import model.Dictionary;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ControlServer {
	private Dictionary dictionary;
	private ServerSocket serverSocket;

	public ControlServer(Dictionary dictionary, int port) throws Exception {
		this.dictionary = dictionary;
		String[] files = { "src/diccionarios/en.json", "src/diccionarios/es.json", "src/diccionarios/fr.json" };
		dictionary.loadData(files);

		serverSocket = new ServerSocket(port);
		System.out.println("Servidor iniciado en el puerto " + port);
	}

	public void listenForClients() {
		while (true) {
			try {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Cliente conectado desde " + clientSocket.getInetAddress());

				ClientHandler clientHandler = new ClientHandler(clientSocket, dictionary);
				new Thread(clientHandler).start();

			} catch (IOException e) {
				System.out.println("Error en la conexión con el cliente: " + e.getMessage());
			}
		}
	}
}
