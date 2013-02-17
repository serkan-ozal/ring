package tr.com.t2giants.ring.client.model;

public class Friendship {

	private long id;
	private double latitude;
	private double longitude;
	private FriendshipType friendshipType;
	private byte[] avatar;
	
	public Friendship() {
		
	}
	
	public Friendship(long id, double latitude, double longitude, FriendshipType friendshipType) {
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.friendshipType = friendshipType;
	}
	
	public Friendship(long id, double latitude, double longitude, FriendshipType friendshipType, byte[] avatar) {
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.friendshipType = friendshipType;
		this.avatar = avatar;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
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
