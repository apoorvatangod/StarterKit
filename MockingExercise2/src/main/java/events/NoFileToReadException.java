package events;

import java.io.IOException;

public class NoFileToReadException extends IOException {
	private static final long serialVersionUID = -3233141318905409408L;
	public NoFileToReadException() {
		
	}
	public NoFileToReadException(String message) {
		super(message);
	}

}
