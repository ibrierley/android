package com.example.gltest;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;

public class GLActivity extends Activity {
	
	/** The OpenGL view */
	private GLSurfaceView glSurfaceView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // requesting to turn the title OFF
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // making it full screen
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
		final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;

        // Initiate the Open GL view and
        // create an instance with this activity
        glSurfaceView = new GLSurfaceView(this);
 
        if( supportsEs2 ) {
        	glSurfaceView.setEGLContextClientVersion(2);
            // set our renderer to be the main renderer with
            // the current activity context
            glSurfaceView.setRenderer(new GLRenderer());
            setContentView(glSurfaceView);
        } else {
        	return;
        }
 
    }

	/** Remember to resume the glSurface  */
	@Override
	protected void onResume() {
		super.onResume();
		glSurfaceView.onResume();
	}

	/** Also pause the glSurface  */
	@Override
	protected void onPause() {
		super.onPause();
		glSurfaceView.onPause();
	}
}
