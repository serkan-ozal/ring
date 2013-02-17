package tr.com.t2giants.ring.client.view;

import tr.com.t2giants.ring.client.view.friendship.FriendshipMapActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class RingMainActivity extends BaseRingActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
        setContentView(R.layout.main);
        // TODO Check cookie for auto login
        startActivity(new Intent(this, FriendshipMapActivity.class));
    }

}
