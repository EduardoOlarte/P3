package app;

import control.ControlServer;
import model.Dictionary;

public class AppServer {
	public static void main(String[] args) throws Exception {
		Dictionary dictionary = new Dictionary();
		ControlServer controlServer = new ControlServer(dictionary, 5000);
		controlServer.listenForClients();

	}
}
