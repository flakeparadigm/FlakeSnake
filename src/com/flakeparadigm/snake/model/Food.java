package com.flakeparadigm.snake.model;

import java.awt.Image;
import java.awt.Point;

public class Food {
	private FoodE type;
	private int value;
	private Image img;
	private Point location;

	public Food(FoodE type, Point location) {
		// set food name
		this.type = type;

		// set location for food item
		this.location = location;

		// load the image
		img = ImageBuffer.getImage(type.name().toLowerCase());
	}

	// getters
	public FoodE getType() {
		return type;
	}

	public int getPoints() {
		return type.getPoints();
	}

	public Image getImg() {
		return img;
	}
	
	public Point getLocation() {
		return location;
	}
}
