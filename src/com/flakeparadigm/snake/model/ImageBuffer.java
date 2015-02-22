package com.flakeparadigm.snake.model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageBuffer {

	private static HashMap<String, Image> loadedImages = new HashMap<String, Image>();
	
	public static Image getImage(String name) {
		// check to see if the image has been loaded
		Image img = loadedImages.get(name);
		if( img != null )
			return img;
		
		// if image hasn't been loaded yet, load it.
		// load the image
		String s = "imgs/" + name + ".png";
		try {
			img = ImageIO.read(new File(s));
			loadedImages.put(name, img);
		} catch (IOException e) {
			img = null;
			e.printStackTrace();
		}
		
		return img;
		
	}
}
