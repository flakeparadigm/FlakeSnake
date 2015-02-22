package com.flakeparadigm.snake.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum FoodE {
	PELLET(10), CHERRY(100), PIZZA(100);

	private int points;
	
	FoodE(int p) {
		points = p;
	}

	public int getPoints() {
		return points;
	}
	
	// statics for random
	private static final List<FoodE> FOODS = Collections
			.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = FOODS.size();
	private static final Random RAND = new Random();
	
	// get random food
	public static FoodE randomFood() {
		if( RAND.nextInt(4) > 2 )
			return FOODS.get(RAND.nextInt(SIZE-1) + 1);
		else
			return FOODS.get(0);
	}

}
