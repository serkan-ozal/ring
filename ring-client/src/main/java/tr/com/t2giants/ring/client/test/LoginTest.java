package tr.com.t2giants.ring.client.test;

import tr.com.t2giants.ring.client.model.LoginRequest;
import tr.com.t2giants.ring.client.model.LoginResponse;
import tr.com.t2giants.ring.client.service.RingService;
import tr.com.t2giants.ring.client.service.RingServiceRestImpl;

public class LoginTest {

	public static void main(String[] args) {
		RingService ringService = RingServiceRestImpl.getRingService();
		LoginResponse loginResponse = ringService.login(new LoginRequest("serkan", "admin"));
		System.out.println(loginResponse.getToken());
	}

}
