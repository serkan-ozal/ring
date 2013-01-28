package tr.com.t2giants.ring.client.model;

import org.codehaus.jackson.annotate.JsonProperty;

import tr.com.t2giants.ring.client.service.RingServiceRestImpl;

public class LoginResponse {

	@JsonProperty(value=RingServiceRestImpl.TOKEN_PARAMETER)
	private String token;
	private Throwable error;
	
	public LoginResponse() {
		
	}
	
	public LoginResponse(String token) {
		this.token = token;
	}
	
	public LoginResponse(Throwable error) {
		this.error = error;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public Throwable getError() {
		return error;
	}
	
	public void setError(Throwable error) {
		this.error = error;
	}
	
	public boolean isSuccessful() {
		return token != null && token.length() > 0;
	}
	
}
