package app;

import control.Control;
import model.Dictionary;
import view.View;

public class App {
	public static void main(String[] args) throws Exception {
		Dictionary dictionary = new Dictionary();
		View view = new View();
		Control control = new Control(dictionary, view);

	}
}
