package tr.com.t2giants.ring.client.view.friendship;

import tr.com.t2giants.ring.client.view.BaseRingActivity;

import android.os.Bundle;
import android.util.Log;

public class FriendshipActivity extends BaseRingActivity {

    private static String TAG = "ring-friendship";
    
    private FriendshipMonitoringView friendshipMonitoringView;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
		
		setTitle("Friendship Monitor");
		friendshipMonitoringView = new FriendshipMonitoringView(this);
        setContentView(friendshipMonitoringView);
    }
    
}
