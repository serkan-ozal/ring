package tr.com.t2giants.ring.client.service;

import java.util.List;

import tr.com.t2giants.ring.client.model.Friendship;
import tr.com.t2giants.ring.client.model.LoginRequest;
import tr.com.t2giants.ring.client.model.LoginResponse;

public interface RingService {

	public LoginResponse login(LoginRequest loginRequest);
	public List<Friendship> getFriendships();
	public void informCurrentLocation(double lat, double lon);
	public void sendRequestForAddingToRing(long id);
	public void sendRequestForRemovingFromRing(long id);
	public void sendRequestForDiscardingRingRequest(long id);
	
}
