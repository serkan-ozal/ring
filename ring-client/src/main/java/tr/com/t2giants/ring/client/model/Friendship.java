package tr.com.t2giants.ring.client.model;

public class Friendship {

	private double latitude;
	private double longitude;
	private FriendshipType friendshipType;
	
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
	
}
