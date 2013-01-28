package tr.com.t2giants.ring.client.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import tr.com.t2giants.ring.client.model.LoginRequest;
import tr.com.t2giants.ring.client.model.LoginResponse;
import tr.com.t2giants.ring.client.util.JsonUtil;
import tr.com.t2giants.ring.client.util.RestConstants;

public class RingServiceRestImpl implements RingService, RestConstants {
	
	@Override
	public LoginResponse login(LoginRequest loginRequest) {
		try {
			HttpClient client = new DefaultHttpClient();
			HttpPost request = new HttpPost(SERVER_URL + LOGIN_PATH);
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair(USERNAME_PARAMETER, loginRequest.getUsername()));
			params.add(new BasicNameValuePair(PASSWORD_PARAMETER, loginRequest.getPassword()));
			request.setEntity(new UrlEncodedFormEntity(params));
			
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String responseBody = client.execute(request, responseHandler);
			return JsonUtil.toObject(responseBody, LoginResponse.class);
		}
		catch (Throwable t) {
			t.printStackTrace();
			return new LoginResponse(t);
		}
	}
	
	public static void main(String[] args) {
		RingService ringService = new RingServiceRestImpl();
		LoginResponse loginResponse = ringService.login(new LoginRequest("abc", "admin"));
		System.out.println(loginResponse.getToken());
	}

}
