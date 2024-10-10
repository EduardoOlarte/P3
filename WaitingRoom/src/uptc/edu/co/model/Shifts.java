package uptc.edu.co.model;

import uptc.edu.co.event.CustomEvent;
import uptc.edu.co.event.CustomEventListener;
import uptc.edu.co.event.CustomEventResponse;
import uptc.edu.co.structure.MyQueue;

public class Shifts {
	private MyQueue<String> queue;
	private CustomEventListener listener;

	public Shifts() {
		queue = new MyQueue<>();
	}

	public void setEventListener(CustomEventListener listener) {
		this.listener = listener;
	}

	public void addPerson(String name, int priority) {
		queue.push(name, priority);
		if (listener != null) {
			CustomEvent event = new CustomEvent("addPerson", name, priority);
			CustomEventResponse response = listener.handleEvent(event);
		}
	}

	public void takePerson() {
		if (!queue.isEmpty()) {
			String person = queue.poll();
			if (listener != null) {
				CustomEvent event = new CustomEvent("takePerson", person, 0);
				CustomEventResponse response = listener.handleEvent(event);
			}
		}
	}

	public boolean isEmpty() {
		return queue.isEmpty();
	}
}
