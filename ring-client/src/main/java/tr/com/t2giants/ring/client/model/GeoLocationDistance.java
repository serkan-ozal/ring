package tr.com.t2giants.ring.client.model;

public class GeoLocationDistance {

	private float totalDistanceInMeters;
	private float verticalDistanceInMeters;
	private float horizontalDistanceInMeters;
	
	public GeoLocationDistance() {
		
	}
	
	public GeoLocationDistance(float totalDistanceInMeters, float verticalDistanceInMeters, float horizontalDistanceInMeters) {
		this.totalDistanceInMeters = totalDistanceInMeters;
		this.verticalDistanceInMeters = verticalDistanceInMeters;
		this.horizontalDistanceInMeters = horizontalDistanceInMeters;
	}

	public float getTotalDistanceInMeters() {
		return totalDistanceInMeters;
	}
	
	public void setTotalDistanceInMeters(float totalDistanceInMeters) {
		this.totalDistanceInMeters = totalDistanceInMeters;
	}
	
	public float getVerticalDistanceInMeters() {
		return verticalDistanceInMeters;
	}
	
	public void setVerticalDistanceInMeters(float verticalDistanceInMeters) {
		this.verticalDistanceInMeters = verticalDistanceInMeters;
	}
	
	public float getHorizontalDistanceInMeters() {
		return horizontalDistanceInMeters;
	}
	
	public void setHorizontalDistanceInMeters(float horizontalDistanceInMeters) {
		this.horizontalDistanceInMeters = horizontalDistanceInMeters;
	}
	
}
