package tr.com.t2giants.ring.client.view.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.location.Location;

import tr.com.t2giants.ring.client.model.GeoLocationDistance;
import tr.com.t2giants.ring.client.view.R;

public class RingClientUtil {

	private static Resources resources;
	private static Drawable okIcon;
    private static Drawable cancelIcon;
	
	private RingClientUtil() {
		
	}
	
	private static void initIfNeeded(Context context) {
		if (resources == null) {
			resources = context.getResources();
			okIcon = resources.getDrawable(R.drawable.ok);
	    	cancelIcon = resources.getDrawable(R.drawable.cancel);
		}
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
	
	public static void showConfirmDialog(Context context, String title, String message, 
			DialogInterface.OnClickListener okListener, DialogInterface.OnClickListener cancelListener) {
		initIfNeeded(context);
		
		final AlertDialog dialog = new AlertDialog.Builder(context).create();
	    dialog.setTitle(title);
	    dialog.setMessage(message);
	    dialog.setCancelable(false);
	    dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", okListener);
	    dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No", cancelListener);
	    dialog.setIcon(R.drawable.question);
	    dialog.show();
	    dialog.getButton(DialogInterface.BUTTON_POSITIVE).setCompoundDrawablesWithIntrinsicBounds(okIcon, null, null, null);
	    dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setCompoundDrawablesWithIntrinsicBounds(cancelIcon, null, null, null);
	}
	
}
