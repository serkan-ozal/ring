package tr.com.t2giants.ring.client.view.friendship;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import tr.com.t2giants.ring.client.view.util.OpenGLUtil;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;

public class FriendshipMonitoringRenderer implements Renderer {

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
    	gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    }

    public void onDrawFrame(GL10 gl) {
    	gl.glClear(GLES20.GL_COLOR_BUFFER_BIT);
    	OpenGLUtil.drawCircle(gl, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.00f);
    	OpenGLUtil.drawCircle(gl, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.75f);
    	OpenGLUtil.drawCircle(gl, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.50f);
    	OpenGLUtil.drawCircle(gl, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.25f);
    }

    public void onSurfaceChanged(GL10 gl, int width, int height) {
    	gl.glViewport(0, 0, width, height);
    }
    
}
