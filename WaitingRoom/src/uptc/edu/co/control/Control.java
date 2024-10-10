package uptc.edu.co.control;

import uptc.edu.co.event.CustomEvent;
import uptc.edu.co.event.CustomEventListener;
import uptc.edu.co.event.CustomEventResponse;
import uptc.edu.co.model.Shifts;
import uptc.edu.co.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Control implements CustomEventListener {
	private Shifts model;
	private View view;

	public Control(Shifts model, View view) {
		this.model = model;
		this.view = view;

		model.setEventListener(this);

		view.addAddButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = view.getNombre();
				int priority = view.getPrioridad();
				if (!name.isEmpty()) {
					model.addPerson(name, priority);
				} else {
					view.showMessage("El nombre no puede estar vacío");
				}
			}
		});

		view.addTakeButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.takePerson();
			}
		});
	}

	@Override
	public CustomEventResponse handleEvent(CustomEvent event) {
		String message;
		boolean success;

		switch (event.getEventType()) {
		case "addPerson":
			message = "Turno agregado: " + event.getNombre();
			success = true;
			break;
		case "takePerson":
			message = "Atendiendo a: " + event.getNombre();
			success = true;
			break;
		default:
			message = "Evento desconocido";
			success = false;
			break;
		}

		view.showMessage(message);

		return new CustomEventResponse(message, success);
	}
}
