package io.github.eviil.fallislava.Engine.Application;

/**
 * Created by Thomas Dotters on 13/03/2016.
 */
public class ApplicationSingleton {
	private static ApplicationSingleton instance;

	public static ApplicationSingleton get() {
		if(instance == null) instance = getSync();
		return instance;
	}

	private static synchronized ApplicationSingleton getSync() {
		if(instance == null) instance = new ApplicationSingleton();
		return instance;
	}

	private ApplicationSingleton(){
		// here you can directly access the Application context calling
		App.get();
	}
}
