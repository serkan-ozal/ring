package tr.com.t2giants.ring.client.exception;

@SuppressWarnings("serial")
public class LoginFailedException extends RingClientException {

	public static final String DEFAULT_MESSAGE = "Login Failed";
	
	public LoginFailedException() {
		super(DEFAULT_MESSAGE);
	}
	
	public LoginFailedException(String message) {
		super(message);
	}
	
	public LoginFailedException(Throwable cause) {
		super(DEFAULT_MESSAGE, cause);
	}
	
	public LoginFailedException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
