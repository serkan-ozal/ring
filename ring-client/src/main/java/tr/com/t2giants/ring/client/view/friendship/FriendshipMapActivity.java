package tr.com.t2giants.ring.client.view.friendship;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import tr.com.t2giants.ring.client.model.Friendship;
import tr.com.t2giants.ring.client.model.FriendshipType;
import tr.com.t2giants.ring.client.view.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class FriendshipMapActivity extends MapActivity implements LocationListener {

    private final String TAG = getClass().getSimpleName();
    
    private final static int HEADER_HEIGHT = 50;
    @SuppressWarnings("unused")
	private final static int HEADER_ICON_HEIGHT = 50;
    private final static int HEADER_ICON_WIDTH = 88;
    
    @SuppressWarnings("unused")
	private final static int FOOTER_HEIGHT = 50;
    private final static int FOOTER_ICON_HEIGHT = 50;
    private final static int FOOTER_ICON_WIDTH = 72;
    
    private final Paint HEADER_PAINT = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint FOOTER_PAINT_1 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint FOOTER_PAINT_2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    
    private LocationManager locationManager;
    private Location currentLocation;
    
    private MapView mapView;
    private MapController mapController;
    private List<Friendship> friendshipList = new ArrayList<Friendship>();
    
    public FriendshipMapActivity() {
    	init();
    }
    
    private void init() {
    	HEADER_PAINT.setColor(0xFFFA3857);
    	HEADER_PAINT.setStyle(Style.FILL);
    	
    	FOOTER_PAINT_1.setColor(0xFFFA3857);
    	FOOTER_PAINT_1.setStyle(Style.FILL);
    	
    	FOOTER_PAINT_2.setColor(0xFFCD2943);
    	FOOTER_PAINT_2.setStyle(Style.FILL);
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
		
		setContentView(R.layout.friendship);

		locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this); 
		
		mapView = (MapView) findViewById(R.id.mapView);
		mapController = mapView.getController();
		
		mapView.setBuiltInZoomControls(true);
		mapController.setZoom(20);
		
		new Timer().
			schedule(
				new TimerTask() {
		            public void run() {
		            	FriendshipMapActivity.this.runOnUiThread(
		            		new Runnable() {
								@Override
								public void run() {
									drawView();
								}
		            		});
		            }
				}, 
				1000, 1000);
		
		new Timer().
	    	schedule(
				new TimerTask() {
		            public void run() {
		            	generateRandomFriendshipList();
		            }
				}, 
				10000, 1000);
    }

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	@Override
	public void onLocationChanged(Location location) {
		if (currentLocation == null) {
			GeoPoint point = 
				new GeoPoint(
			          (int) (location.getLatitude() * 1E6), 
			          (int) (location.getLongitude() * 1E6));
			mapController.animateTo(point);
			mapController.setZoom(20);
		}
		currentLocation = location;
		drawView();
	}
	
	private void drawView() {
		List<Overlay> listOfOverlays = mapView.getOverlays();
		listOfOverlays.clear();

		if (currentLocation != null) {
			GeoPoint point = 
				new GeoPoint(
			          (int) (currentLocation.getLatitude() * 1E6), 
			          (int) (currentLocation.getLongitude() * 1E6));
			
			CurrentLocationOverlay currentLocationOverlay = new CurrentLocationOverlay();
			currentLocationOverlay.setPointToDraw(point);
			listOfOverlays.add(currentLocationOverlay);
			
			for (int i = 0; i < friendshipList.size(); i++) {
				Friendship friendship = friendshipList.get(i);
				FriendshipLocationOverlay friendshipLocationOverlay = new FriendshipLocationOverlay(friendship);
				GeoPoint friendshipLocationPoint = new GeoPoint(
				          (int) (friendship.getLatitude() * 1E6), 
				          (int) (friendship.getLongitude() * 1E6));
				friendshipLocationOverlay.setPointToDraw(friendshipLocationPoint);
				listOfOverlays.add(friendshipLocationOverlay);
			}
		}
		
		listOfOverlays.add(new HeaderOverlay());
		listOfOverlays.add(new FooterOverlay());

		mapView.invalidate();
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
    
	public class CurrentLocationOverlay extends Overlay {
		
		private GeoPoint pointToDraw;

		public void setPointToDraw(GeoPoint point) {
			pointToDraw = point;
		}

		public GeoPoint getPointToDraw() {
			return pointToDraw;
		}
		  
		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
		    super.draw(canvas, mapView, shadow);           

		    Point screenPts = new Point();
		    mapView.getProjection().toPixels(pointToDraw, screenPts);

		    Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.current_location);
		    canvas.drawBitmap(bmp, screenPts.x - (bmp.getWidth() / 2), screenPts.y - (bmp.getHeight() / 2), null);    
		    
		    return true;
		}
		
	}
	
	public class FriendshipLocationOverlay extends Overlay {
		
		private GeoPoint pointToDraw;
		private Friendship friendship;
		
		public FriendshipLocationOverlay(Friendship friendship) {
			this.friendship = friendship;
		}

		public void setPointToDraw(GeoPoint point) {
			pointToDraw = point;
		}

		public GeoPoint getPointToDraw() {
			return pointToDraw;
		}
		
		public Friendship getFriendship() {
			return friendship;
		}
		
		public void setFriendship(Friendship friendship) {
			this.friendship = friendship;
		}
		  
		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
		    super.draw(canvas, mapView, shadow);           

		    int drawableId = 0;
			
			switch (friendship.getFriendshipType()) {
				case IN_RING:
					drawableId = R.drawable.ring_in;
					break;
				case OUT_RING:
					drawableId = R.drawable.ring_out;
					break;
				case RING_REQUESTED:
					drawableId = R.drawable.ring_requested;
					break;	
			}
			
			if (drawableId == 0) {
				return true;
			}
			
		    Point screenPts = new Point();
		    mapView.getProjection().toPixels(pointToDraw, screenPts);

		    Bitmap bmp = BitmapFactory.decodeResource(getResources(), drawableId);
		    canvas.drawBitmap(bmp, screenPts.x - (bmp.getWidth() / 2), screenPts.y - (bmp.getHeight() / 2), null);      	
		    
		    return true;
		}
		
	}
	
	public class HeaderOverlay extends Overlay {
		
		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
			canvas.drawRect(0, 0, mapView.getWidth(), HEADER_HEIGHT, HEADER_PAINT);
			Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ring_header);
		    canvas.drawBitmap(bmp, (mapView.getWidth() - HEADER_ICON_WIDTH) / 2 , 0, null);    
			return true;
		}
		
	}
	
	public class FooterOverlay extends Overlay {
		
		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
			RectF oval1 = new RectF();
			oval1.set(	-500 + (mapView.getWidth() / 2), 
						mapView.getHeight() - FOOTER_ICON_HEIGHT - 20 - 40, 
						+500 + (mapView.getWidth() / 2), 
						mapView.getHeight() - FOOTER_ICON_HEIGHT + 1000 - 40);
			canvas.drawArc(oval1, 0, 360, true, FOOTER_PAINT_1);
			
			RectF oval2 = new RectF();
			oval2.set(	-500 + (mapView.getWidth() / 2), 
						mapView.getHeight() - FOOTER_ICON_HEIGHT - 20, 
						+500 + (mapView.getWidth() / 2), 
						mapView.getHeight() - FOOTER_ICON_HEIGHT + 1000);
			canvas.drawArc(oval2, 0, 360, true, FOOTER_PAINT_2);
			
			Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ring_footer);
		    canvas.drawBitmap(bmp, (mapView.getWidth() - FOOTER_ICON_WIDTH) / 2 , mapView.getHeight() - FOOTER_ICON_HEIGHT, null);    
			return true;
		}
		
	}
	
	private void generateRandomFriendshipList() {
    	friendshipList.clear();
    	for (int i = 0; i < 5; i++) {
    		// Latitude  : 39.865776
    		// Longitude : 32.824917
    		friendshipList.add(
    				new Friendship(
    						39.865776 + (0.0001 * Math.random() * 5), //0.0001 10m difference
    						32.824917 + (0.0001 * Math.random() * 5), //0.0001 10m difference
    						FriendshipType.values()[(int)(Math.random() * 3)]));
    	}
    }

}
