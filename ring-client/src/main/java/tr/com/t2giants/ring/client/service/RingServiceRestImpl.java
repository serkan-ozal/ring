package tr.com.t2giants.ring.client.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import tr.com.t2giants.ring.client.exception.InvalidUsernameOrPasswordException;
import tr.com.t2giants.ring.client.exception.LoginFailedException;
import tr.com.t2giants.ring.client.model.Friendship;
import tr.com.t2giants.ring.client.model.FriendshipType;
import tr.com.t2giants.ring.client.model.LoginRequest;
import tr.com.t2giants.ring.client.model.LoginResponse;
import tr.com.t2giants.ring.client.util.RestConstants;

public class RingServiceRestImpl implements RingService, RestConstants {
	
	private static RingService ringService = new RingServiceRestImpl();
	
	private String authenticationToken;
	
	private RingServiceRestImpl() {
		
	}
	
	public static RingService getRingService() {
		return ringService;
	}
	
	@Override
	public LoginResponse login(LoginRequest loginRequest) {
		try {
			HttpClient client = new DefaultHttpClient();
			HttpPost request = new HttpPost(SERVER_URL + LOGIN_PATH);
			request.addHeader(REMEMBER_ME_PARAMETER, Boolean.TRUE.toString());
			request.addHeader(USERNAME_PARAMETER, loginRequest.getUsername());
			request.addHeader(PASSWORD_PARAMETER, loginRequest.getPassword());

			HttpResponse response = client.execute(request);
			Header tokenHeader = response.getFirstHeader(TOKEN_PARAMETER);
			if (tokenHeader != null) {
				authenticationToken = tokenHeader.getValue();
				if (authenticationToken != null && authenticationToken.length() > 0) {
					return new LoginResponse(authenticationToken);
				}
			}
			return new LoginResponse(new InvalidUsernameOrPasswordException());
		}
		catch (Throwable t) {
			t.printStackTrace();
			return new LoginResponse(new LoginFailedException(t));
		}
	}

	@Override
	public List<Friendship> getFriendships() {
		List<Friendship> friendshipList = new ArrayList<Friendship>();
		for (int i = 0; i < 5; i++) {
			friendshipList.add(
				new Friendship(i,
						39.865776 + (0.0001 * Math.random() * 5), //0.0001 10m difference
						32.824917 + (0.0001 * Math.random() * 5), //0.0001 10m difference
						FriendshipType.values()[(int)(Math.random() * 3)]));
		}
		return friendshipList;
	}

	@Override
	public void informCurrentLocation(double lat, double lon) {
		try {
			HttpClient client = new DefaultHttpClient();
			HttpPost request = new HttpPost(SERVER_URL + INFORM_LOCATION_PATH + URL_SEPARATOR + lat + URL_SEPARATOR + lon);
			request.addHeader(TOKEN_PARAMETER, authenticationToken);
			HttpResponse response = client.execute(request);
			System.out.println(response.getStatusLine().getStatusCode());
		}
		catch (Throwable t) {
			t.printStackTrace();
		}
	}

	@Override
	public void sendRequestForAddingToRing(long id) {
		
	}

	@Override
	public void sendRequestForRemovingFromRing(long id) {
		
	}

	@Override
	public void sendRequestForDiscardingRingRequest(long id) {
		
	}

}
