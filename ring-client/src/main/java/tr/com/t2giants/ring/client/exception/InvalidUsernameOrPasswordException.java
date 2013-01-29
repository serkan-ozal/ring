package tr.com.t2giants.ring.client.exception;

@SuppressWarnings("serial")
public class InvalidUsernameOrPasswordException extends LoginFailedException {

	public static final String DEFAULT_MESSAGE = "Invalid Username / Password";
	
	public InvalidUsernameOrPasswordException() {
		super(DEFAULT_MESSAGE);
	}
	
	public InvalidUsernameOrPasswordException(String message) {
		super(message);
	}
	
	public InvalidUsernameOrPasswordException(Throwable cause) {
		super(DEFAULT_MESSAGE, cause);
	}
	
	public InvalidUsernameOrPasswordException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
