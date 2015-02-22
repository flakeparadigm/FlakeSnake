package com.flakeparadigm.snake.model;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

public class ActiveFoods implements Collection<Food> {

	private ArrayList<Food> foods;
	private Level level;
	private Random rand;

	public ActiveFoods(Level level) {
		foods = new ArrayList<Food>();
		rand = new Random();
		
		initFoods(level);
	}

	public void initFoods(Level level) {
		this.level = level;
		clear();
		spawnFood();
	}

	private void spawnFood() {
		Point newFoodLoc = new Point();
		Dimension mapSize = level.getMapSize();
		boolean uniqueLocation = false;
		
		// get a random location. Try until it is a new location
		while( !uniqueLocation ) {
			newFoodLoc.x = rand.nextInt(mapSize.width);
			newFoodLoc.y = rand.nextInt(mapSize.height);
			
			if( level.canMoveTo(newFoodLoc) && ( foodHere(newFoodLoc) == null ) )
				uniqueLocation = true;
		}

		foods.add(new Food(FoodE.randomFood(), newFoodLoc));
	}
	
	public Food checkNoms(Point p) {
		Food f = foodHere(p);
		
		if( f != null ) {
			foods.remove(f);
			spawnFood();
			
			return f;
		}
		return null;
	}
	
	private Food foodHere(Point p) {
		for( Food f : foods ) {
			if( f.getLocation().equals(p) ) {
				return f;
			}
		}
		return null;
	}

	@Override
	public boolean add(Food f) {
		return foods.add(f);
	}

	@Override
	public boolean addAll(Collection<? extends Food> f) {
		return foods.addAll(f);
	}

	@Override
	public void clear() {
		foods.clear();
	}

	@Override
	public boolean contains(Object f) {
		return foods.contains(f);
	}

	@Override
	public boolean containsAll(Collection<?> f) {
		return foods.containsAll(f);
	}

	@Override
	public boolean isEmpty() {
		return foods.isEmpty();
	}

	@Override
	public Iterator<Food> iterator() {
		return foods.iterator();
	}

	@Override
	public boolean remove(Object f) {
		return foods.remove(f);
	}

	@Override
	public boolean removeAll(Collection<?> f) {
		return foods.removeAll(f);
	}

	@Override
	public boolean retainAll(Collection<?> f) {
		return foods.retainAll(f);
	}

	@Override
	public int size() {
		return foods.size();
	}

	@Override
	public Object[] toArray() {
		return foods.toArray();
	}

	@Override
	public <T> T[] toArray(T[] f) {
		return foods.toArray(f);
	}
}
