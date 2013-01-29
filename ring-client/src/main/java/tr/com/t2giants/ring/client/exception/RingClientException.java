package tr.com.t2giants.ring.client.exception;

@SuppressWarnings("serial")
public class RingClientException extends Exception {

	public RingClientException() {

	}
	
	public RingClientException(String message) {
		super(message);
	}
	
	public RingClientException(Throwable cause) {
		super(cause);
	}
	
	public RingClientException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
