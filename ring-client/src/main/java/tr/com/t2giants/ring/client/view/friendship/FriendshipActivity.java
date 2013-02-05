package tr.com.t2giants.ring.client.view.friendship;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.google.android.maps.MapActivity;

import tr.com.t2giants.ring.client.model.Friendship;
import tr.com.t2giants.ring.client.model.FriendshipType;
import tr.com.t2giants.ring.client.view.BaseRingActivity;
import tr.com.t2giants.ring.client.view.R;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class FriendshipActivity extends MapActivity {

    private static String TAG = "ring-friendship";
    
    private FriendshipMonitoringView friendshipMonitoringView;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
		
		/*
		setTitle("Friendship Monitor");
		friendshipMonitoringView = 
				new FriendshipMonitoringView((LocationManager)getSystemService(Context.LOCATION_SERVICE), this);
        
        new Timer().
        	schedule(
    			new TimerTask() {
    	            public void run() {
    	            	friendshipMonitoringView.setFriendshipList(generateRandomFriendshipList());
    	            }
    			}, 
    			10000, 1000);
        */
        setContentView(R.layout.friendship);
    }
    
    private List<Friendship> generateRandomFriendshipList() {
    	List<Friendship> friendshipList = new ArrayList<Friendship>();
    	for (int i = 0; i < 5; i++) {
    		// Latitude  : 37,422006
    		// Longitude : -122,084095
    		friendshipList.add(
    				new Friendship(
    						37.4220 + (0.0001 * Math.random() * 5), //0.0001 10m difference
    						-122.0840 + (0.0001 * Math.random() * 5), //0.0001 10m difference
    						FriendshipType.values()[(int)(Math.random() * 3)]));
    	}
    	return friendshipList;
    }

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
    
}
