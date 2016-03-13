package io.github.eviil.fallislava.Engine.Application;

import android.app.Application;

/**
 * Created by Thomas Dotters on 13/03/2016.
 */
public class App extends Application {
	private static App instance;
	public static App get() { return instance; }

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
	}
}
