package io.github.eviil.fallislava.Engine.Application;

import android.app.Application;
import android.content.Context;

/**
 * Created by Thomas Dotters on 13/03/2016.
 */
public class App extends Application {

    public static final String TAG = "GAME ENGINE";
	private static App instance;

	public static App getInstance() {
		return instance;
	}

	public static Context getContext(){
		return instance.getApplicationContext();
	}

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
	}
}
