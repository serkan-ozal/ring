package tr.com.t2giants.ring.client.model;

public class Friendship {

	private double latitude;
	private double longitude;
	private FriendshipType friendshipType;
	private byte[] avatar;
	
	public Friendship() {
		
	}
	
	public Friendship(double latitude, double longitude, FriendshipType friendshipType) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.friendshipType = friendshipType;
	}
	
	public Friendship(double latitude, double longitude, FriendshipType friendshipType, byte[] avatar) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.friendshipType = friendshipType;
		this.avatar = avatar;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public FriendshipType getFriendshipType() {
		return friendshipType;
	}
	
	public void setFriendshipType(FriendshipType friendshipType) {
		this.friendshipType = friendshipType;
	}
	
	public byte[] getAvatar() {
		return avatar;
	}
	
	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}
	
}
