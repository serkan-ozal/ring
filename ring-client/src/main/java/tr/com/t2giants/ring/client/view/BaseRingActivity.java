package tr.com.t2giants.ring.client.view;

import android.app.Activity;
import tr.com.t2giants.ring.client.service.RingService;
import tr.com.t2giants.ring.client.service.RingServiceRestImpl;

public abstract class BaseRingActivity extends Activity {

	protected final String TAG = getClass().getSimpleName();
	
	protected RingService ringService = RingServiceRestImpl.getRingService();
	
}
