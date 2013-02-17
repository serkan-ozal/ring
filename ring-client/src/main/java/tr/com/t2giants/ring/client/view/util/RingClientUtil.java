package tr.com.t2giants.ring.client.view.util;

import android.location.Location;

import tr.com.t2giants.ring.client.model.GeoLocationDistance;

public class RingClientUtil {

	private RingClientUtil() {
		
	}
	
	public static GeoLocationDistance findGeolocationDistance(double startLatitude, double startLongitude, 
			double endLatitude, double endLongitude) {
		float[] results = new float[1];

		Location.distanceBetween(startLatitude, startLongitude, startLatitude, endLongitude, results);
		float verticalDistanceInMeters = results[0];
		
		Location.distanceBetween(startLatitude, startLongitude, endLatitude, startLongitude, results);
		float horizontalDistanceInMeters = results[0];
		
		Location.distanceBetween(startLatitude, startLongitude, endLatitude, endLongitude, results);
		float totalDistanceInMeters = results[0];
		
		return new GeoLocationDistance(totalDistanceInMeters, verticalDistanceInMeters, horizontalDistanceInMeters);
	}
	
}
