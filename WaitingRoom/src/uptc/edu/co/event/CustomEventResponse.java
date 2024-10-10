package uptc.edu.co.event;

public class CustomEventResponse {
	private String message;
	private boolean success;

	public CustomEventResponse(String message, boolean success) {
		this.message = message;
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public boolean isSuccess() {
		return success;
	}
}
