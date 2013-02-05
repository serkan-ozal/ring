package tr.com.t2giants.ring.client.view.friendship;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import tr.com.t2giants.ring.client.model.Friendship;
import tr.com.t2giants.ring.client.model.FriendshipType;
import tr.com.t2giants.ring.client.model.GeoLocationDistance;
import tr.com.t2giants.ring.client.view.util.RingClientUtil;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class FriendshipMonitoringView extends View implements LocationListener {
	
	private static final int FRIENDSHIP_POINT_RADIUS = 5;
	private static final int FRIENDSHIP_RADAR_LIMIT_IN_METERS = 50;
	
	private LocationManager locationManager;
	private Timer timer = new Timer();
	
	private double latitude;  
	private double longitude;  
	
	private int width; 
	private int height;
	private int centerX;
	private int centerY;
	
	private int radarRadius;
	private Paint radarPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	
	private Paint inRingPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private Paint outRingPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private Paint ringRequestedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	
	private List<Friendship> friendshipList;
	
	public FriendshipMonitoringView(LocationManager locationManager, Context context) {
		super(context);
		this.locationManager = locationManager;
		init();
	}

	public FriendshipMonitoringView(LocationManager locationManager, Context context, AttributeSet attrs) {
		super(context, attrs);
		this.locationManager = locationManager;
		init();
	}

	public FriendshipMonitoringView(LocationManager locationManager, Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.locationManager = locationManager;
		init();
	}
	
	public List<Friendship> getFriendshipList() {
		return friendshipList;
	}
	
	public void setFriendshipList(List<Friendship> friendshipList) {
		this.friendshipList = friendshipList;
	}
	
	private void init() {
		radarPaint.setColor(Color.GREEN);
		radarPaint.setStyle(Style.STROKE);
		
		inRingPaint.setColor(Color.BLUE);
		inRingPaint.setStyle(Style.FILL);
		
		outRingPaint.setColor(Color.RED);
		outRingPaint.setStyle(Style.FILL);
		
		ringRequestedPaint.setColor(Color.YELLOW);
		ringRequestedPaint.setStyle(Style.FILL);
		
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this); 
		
		timer.schedule(
			new TimerTask() {
	            public void run() {
	            	Log.i("FriendShipMonitoringView", "View update task is running ...");
	                FriendshipMonitoringView.this.postInvalidate();
	            }
			}, 
			1000, 1000);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		drawRadar(canvas);
		drawFriendships(canvas);
	}
	
	private void drawRadar(Canvas canvas) {
		width = getWidth(); 
		height = getHeight();
		centerX = width / 2;
		centerY = height / 2;
		radarRadius = width;
		
		for (int currentRadarRadius = radarRadius; currentRadarRadius > 0; currentRadarRadius -= 20) {
			canvas.drawCircle(centerX, centerY, currentRadarRadius, radarPaint);
		}
	}
	
	private void drawFriendships(Canvas canvas) {
		if (friendshipList != null) {
			for (Friendship friendship : friendshipList) {
				GeoLocationDistance distance = 
						RingClientUtil.findGeolocationDistance(latitude, longitude, 
								friendship.getLatitude(), friendship.getLongitude());
				int x = centerX + (int)(distance.getHorizontalDistanceInMeters() * radarRadius / 
										FRIENDSHIP_RADAR_LIMIT_IN_METERS);
				int y = centerY + (int)(distance.getVerticalDistanceInMeters() * radarRadius / 
										FRIENDSHIP_RADAR_LIMIT_IN_METERS);
				drawFriendship(canvas, x, y, friendship.getFriendshipType());
			}
		}
	}
	
	private void drawFriendship(Canvas canvas, int x, int y, FriendshipType friendshipType) {
		Paint friendshipPaint = null;
		switch (friendshipType) {
			case IN_RING:
				friendshipPaint = inRingPaint;
				break;
			case OUT_RING:
				friendshipPaint = outRingPaint;
				break;
			case RING_REQUESTED:
				friendshipPaint = ringRequestedPaint;
				break;	
		}
		if (friendshipPaint == null) {
			return;
		}
		canvas.drawCircle(centerX, centerY, FRIENDSHIP_POINT_RADIUS, friendshipPaint);
	}

	@Override
	public void onLocationChanged(Location location) {
		latitude = location.getLatitude();
		longitude = location.getLongitude();
		Log.i("FriendshipMonitoringView", "Location: " + latitude + ", " + longitude);
	}

	@Override
	public void onProviderDisabled(String provider) {
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		
	}
	
}
