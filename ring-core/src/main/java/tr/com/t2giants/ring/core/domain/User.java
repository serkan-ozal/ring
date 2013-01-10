package tr.com.t2giants.ring.core.domain;

import java.util.Date;
import java.util.List;

public class User {

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
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getTwitterAccountName() {
		return twitterAccountName;
	}
	
	public void setTwitterAccountName(String twitterAccountName) {
		this.twitterAccountName = twitterAccountName;
	}
	
	public String getFacebookAccountName() {
		return facebookAccountName;
	}
	
	public void setFacebookAccountName(String facebookAccountName) {
		this.facebookAccountName = facebookAccountName;
	}
	
	public byte[] getImage() {
		return image;
	}
	
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public List<User> getConnectedUsers() {
		return connectedUsers;
	}
	
	public void setConnectedUsers(List<User> connectedUsers) {
		this.connectedUsers = connectedUsers;
	}
	
	public List<User> getUsersWaitingForAccept() {
		return usersWaitingForAccept;
	}
	
	public void setUsersWaitingForAccept(List<User> usersWaitingForAccept) {
		this.usersWaitingForAccept = usersWaitingForAccept;
	}
	
}
