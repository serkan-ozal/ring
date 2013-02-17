package tr.com.t2giants.ring.client.view.friendship;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import tr.com.t2giants.ring.client.model.Friendship;
import tr.com.t2giants.ring.client.model.FriendshipType;
import tr.com.t2giants.ring.client.view.BaseRingActivity;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class FriendshipRadarActivity extends BaseRingActivity {

    private FriendshipMonitoringView friendshipMonitoringView;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");

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

}
