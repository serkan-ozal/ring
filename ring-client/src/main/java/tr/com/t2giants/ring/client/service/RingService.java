package tr.com.t2giants.ring.client.service;

import tr.com.t2giants.ring.client.model.LoginRequest;
import tr.com.t2giants.ring.client.model.LoginResponse;

public interface RingService {

	public LoginResponse login(LoginRequest loginRequest);
	
}
