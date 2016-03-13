package io.github.eviil.fallislava.Engine;

/**
 * Created by p14137485 on 10/03/2016.
 */
public class ImageHandler {
	private static ImageHandler ourInstance = new ImageHandler();

	public static ImageHandler getInstance() {
		return ourInstance;
	}


    private ImageHandler() {
    }
}
