package tr.com.t2giants.ring.client.view.friendship;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

import android.util.AttributeSet;
import android.view.View;

public class FriendshipMonitoringView extends View {
	
	private int width; 
	private int height;
	private int centerX;
	private int centerY;
	
	private int radarRadius;
	private Paint radarPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	
	public FriendshipMonitoringView(Context context) {
		super(context);
		init();
	}

	public FriendshipMonitoringView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public FriendshipMonitoringView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	private void init() {
		radarPaint.setColor(Color.GREEN);
		radarPaint.setStyle(Style.STROKE);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		drawRadar(canvas);
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
	
}
