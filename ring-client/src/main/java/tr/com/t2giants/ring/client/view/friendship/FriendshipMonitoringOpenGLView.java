package tr.com.t2giants.ring.client.view.friendship;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class FriendshipMonitoringOpenGLView extends GLSurfaceView {

    public FriendshipMonitoringOpenGLView(Context context){
        super(context);
        setRenderer(new FriendshipMonitoringRenderer());
    }
    
}