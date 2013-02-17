package tr.com.t2giants.ring.client.test;

import tr.com.t2giants.ring.client.model.LoginRequest;
import tr.com.t2giants.ring.client.service.RingService;
import tr.com.t2giants.ring.client.service.RingServiceRestImpl;

public class UpdateLocationTest {

	public static void main(String[] args) {
		RingService ringService = RingServiceRestImpl.getRingService();
		ringService.login(new LoginRequest("serkan", "admin"));
		ringService.informCurrentLocation(11, 12);
	}

}
