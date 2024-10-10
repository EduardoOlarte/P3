package uptc.edu.co.app;

import uptc.edu.co.control.Control;
import uptc.edu.co.model.Shifts;
import uptc.edu.co.view.View;

public class Main {
	public static void main(String[] args) {
		Shifts model = new Shifts();
		View view = new View();
		Control control = new Control(model, view);

	}
}
