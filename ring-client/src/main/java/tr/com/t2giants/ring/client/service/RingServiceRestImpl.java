package tr.com.t2giants.ring.client.service;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import tr.com.t2giants.ring.client.exception.InvalidUsernameOrPasswordException;
import tr.com.t2giants.ring.client.exception.LoginFailedException;
import tr.com.t2giants.ring.client.model.LoginRequest;
import tr.com.t2giants.ring.client.model.LoginResponse;
import tr.com.t2giants.ring.client.util.RestConstants;

public class RingServiceRestImpl implements RingService, RestConstants {
	
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
				String token = tokenHeader.getValue();
				if (token != null && token.length() > 0) {
					return new LoginResponse(token);
				}
			}
			return new LoginResponse(new InvalidUsernameOrPasswordException());
		}
		catch (Throwable t) {
			t.printStackTrace();
			return new LoginResponse(new LoginFailedException(t));
		}
	}
	
	public static void main(String[] args) {
		RingService ringService = new RingServiceRestImpl();
		LoginResponse loginResponse = ringService.login(new LoginRequest("abc", "admin"));
		System.out.println(loginResponse.getToken());
	}

}
