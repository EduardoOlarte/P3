package app;

import control.ControlClient;
import view.View;

public class AppClient {
	public static void main(String[] args) throws Exception {

		View view = new View();
		ControlClient controlClient = new ControlClient(view);
		controlClient.initController();
	}
}
