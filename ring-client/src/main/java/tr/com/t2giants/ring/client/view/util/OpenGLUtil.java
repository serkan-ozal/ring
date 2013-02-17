package tr.com.t2giants.ring.client.view.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class OpenGLUtil {

	private static final int FULL_DEGREE = 360;
	private static final int POINT_PER_DEGREE = 2;
	private static final int CIRCLE_POINTS = FULL_DEGREE * POINT_PER_DEGREE;
	
	private static final float[] CIRCLE_VERTICES = new float[CIRCLE_POINTS];
	private static FloatBuffer circleVertBuff;
	
	static {
		initVertices();
	}
	
	private OpenGLUtil() {
		
	}
	
	private static void initVertices() {
		initCircleVertices();
	}
	
	private static void initCircleVertices() {
		for (int i = 0; i < CIRCLE_VERTICES.length; i += POINT_PER_DEGREE) {
			CIRCLE_VERTICES[i]   = (float) (Math.cos(Math.toRadians(i)));
			CIRCLE_VERTICES[i+1] = (float) (Math.sin(Math.toRadians(i)));
		}
		
		ByteBuffer bBuff = ByteBuffer.allocateDirect(CIRCLE_VERTICES.length * (Float.SIZE / Byte.SIZE));    
		bBuff.order(ByteOrder.nativeOrder());
		circleVertBuff = bBuff.asFloatBuffer();
		circleVertBuff.put(CIRCLE_VERTICES);
		circleVertBuff.position(0);
	}
	
	public static void drawCircle(GL10 gl, float red, float green, float blue, 
			float centerX, float centerY, float radius) {
    	gl.glPushMatrix();
    	gl.glScalef(radius, radius, 1.0f);
        gl.glTranslatef(centerX, centerY, 0);
        gl.glColor4f(red, green, blue, 1.0f); 
        gl.glVertexPointer(POINT_PER_DEGREE, GL10.GL_FLOAT, 0, circleVertBuff);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, FULL_DEGREE);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glPopMatrix();
    }
	
}
