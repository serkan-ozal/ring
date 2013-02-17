package tr.com.t2giants.ring.client.util;

public interface RestConstants {

	public static final String URL_SEPARATOR = "/";
	
	public static final String SERVER_URL = "http://ec2-50-16-164-104.compute-1.amazonaws.com:8080/";
	
	public static final String LOGIN_PATH = "j_spring_security_check";
	public static final String USERNAME_PARAMETER = "j_username";
	public static final String PASSWORD_PARAMETER = "j_password";
	public static final String REMEMBER_ME_PARAMETER = "_spring_security_remember_me";
	public static final String TOKEN_PARAMETER = "SPRING_SECURITY_REMEMBER_ME_COOKIE";
	
	public static final String INFORM_LOCATION_PATH = "api/locations/add-current";
	
}
