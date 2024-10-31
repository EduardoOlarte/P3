package control;

import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class ControlClient {
	private View view;
	private String serverAddress;
	private int port;
	private Socket socket;
	private PrintWriter output;
	private BufferedReader input;

	public ControlClient(View view, String serverAddress, int port) {
		this.view = view;
		this.serverAddress = serverAddress;
		this.port = port;

		view.setVisible(true);

	}

	public void connectToServer() {
		try {
			socket = new Socket(serverAddress, port);
			output = new PrintWriter(socket.getOutputStream(), true);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("Conectado al servidor en " + serverAddress + ":" + port);
		} catch (IOException e) {
			e.printStackTrace();
			view.showMessage("Error al conectar al servidor.");
		}
	}

	public void initController() {
		view.getTranslationButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				translateWord();
			}
		});

		view.getAddButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addWord();
			}
		});

		view.getInfoButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showDictionariesInfo();
			}
		});

		view.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				disconnectFromServer();
			}
		});
	}

	private void disconnectFromServer() {
		try {
			if (socket != null) {
				socket.close();
				System.out.println("Desconectado del servidor.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void translateWord() {
		String word = view.getWordField().getText().trim();
		int type = view.getTranslationComboBox().getSelectedIndex();

		if (word.isEmpty()) {
			view.showMessage("Por favor, ingrese una palabra para traducir.");
			return;
		}

		output.println("translate;" + word + ";" + type);
		try {
			String response = input.readLine();
			view.showTranslation(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addWord() {
		String original = view.getOriginalField().getText().trim();
		String translation = view.getTranslationField().getText().trim();
		int type = view.getAddComboBox().getSelectedIndex();

		if (original.isEmpty() || translation.isEmpty()) {
			view.showMessage("Por favor, complete ambos campos (Palabra y Traducción).");
			return;
		}

		output.println("add;" + original + ";" + translation + ";" + type);
		try {
			String response = input.readLine();
			view.showMessage(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showDictionariesInfo() {
		output.println("info");
		StringBuilder responseBuilder = new StringBuilder();

		try {
			String response;
			while (!(response = input.readLine()).equals("END")) {
				responseBuilder.append(response).append("\n");
			}
			view.showMessage(responseBuilder.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
