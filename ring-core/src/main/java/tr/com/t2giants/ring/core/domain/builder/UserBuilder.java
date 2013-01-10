package tr.com.t2giants.ring.core.domain.builder;

import java.util.Date;
import java.util.List;

import tr.com.t2giants.ring.core.domain.User;

public class UserBuilder extends BaseBuilder<User> {

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String twitterAccountName;
	private String facebookAccountName;
	private byte[] image;
	private List<User> connectedUsers;
	private List<User> usersWaitingForAccept;
	
	public UserBuilder username(String username) {
		this.username = username;
		return this;
	}
	
	public UserBuilder password(String password) {
		this.password = password;
		return this;
	}
	
	public UserBuilder firstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	
	public UserBuilder lastName(String lastName) {
		this.lastName = lastName;
		return this;
	}
	
	public UserBuilder birthDate(Date birthDate) {
		this.birthDate = birthDate;
		return this;
	}
	
	public UserBuilder twitterAccountName(String twitterAccountName) {
		this.twitterAccountName = twitterAccountName;
		return this;
	}
	
	public UserBuilder facebookAccountName(String facebookAccountName) {
		this.facebookAccountName = facebookAccountName;
		return this;
	}
	
	public UserBuilder image(byte[] image) {
		this.image = image;
		return this;
	}
	
	public UserBuilder connectedUsers(List<User> connectedUsers) {
		this.connectedUsers = connectedUsers;
		return this;
	}
	
	public UserBuilder usersWaitingForAccept(List<User> usersWaitingForAccept) {
		this.usersWaitingForAccept = usersWaitingForAccept;
		return this;
	}
	
	@Override
	public User build() {
		User user = new User();
		
		user.setUsername(username);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setBirthDate(birthDate);
		user.setTwitterAccountName(twitterAccountName);
		user.setFacebookAccountName(facebookAccountName);
		user.setImage(image);
		user.setConnectedUsers(connectedUsers);
		user.setUsersWaitingForAccept(usersWaitingForAccept);
		
		return user;
	}

}
