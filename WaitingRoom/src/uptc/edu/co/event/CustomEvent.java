package uptc.edu.co.event;

public class CustomEvent {
	private String eventType;
	private String name;
	private int priority;

	public CustomEvent(String eventType, String name, int priority) {
		this.eventType = eventType;
		this.name = name;
		this.priority = priority;
	}

	public String getEventType() {
		return eventType;
	}

	public String getNombre() {
		return name;
	}

	public int getPrioridad() {
		return priority;
	}
}
