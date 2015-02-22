package com.flakeparadigm.snake.model;

import java.awt.Point;
import java.util.LinkedList;

public class Player {

	// private int length;
	private int score;
	private String name;
	private char direction;
	private Point directionVector = new Point();
	private Point startPoint;
	private boolean addLength;
	private Level level;
	private Point tmpPoint = new Point();

	private LinkedList<Point> segments = new LinkedList<Point>();

	public Player(String name) {
		this.name = name;
		// length = 2;
		score = 0;
	}

	public Player(String name, Level level) {
		this.name = name;
		initPlayer(level);

		score = 0;
	}

	// initializers
	public void initPlayer(Level level) {
		segments.clear();
		setDirection(level.getStartDirection());
		startPoint = level.getStartPoint();
		this.level = level;

		segments.push(startPoint);
		addLength = true;
	}

	// setters
	public boolean movePlayer() {
		Point newHead;
		Point oldHead = segments.peekLast();
		
		tmpPoint.x = oldHead.x + directionVector.x;
		tmpPoint.y = oldHead.y + directionVector.y;
		// if we can move, then do it
		if (canMoveTo(tmpPoint)) {
			
			if (addLength) {
				newHead = tmpPoint;
				tmpPoint = new Point();
				addLength = false;
			} else {
				newHead = tmpPoint;
				tmpPoint = segments.poll();
			}
			
			segments.offer(newHead);
			return true;
		}

		// if we can't, then die
		return false;
	}

	// check if player can move to a place
	private boolean canMoveTo(Point newPoint) {
		boolean answer = level.canMoveTo(newPoint);
		
		for( Point s : segments ) {
			answer = answer && (!newPoint.equals(s));
		}
		
		return answer;
	}

	public void addScore(int points) {
		score += points;
		addLength = true;
	}

	public void setDirection(char newDirection) {
		switch (newDirection) {
		case '^':
			if ((direction == '^') || (direction == 'V'))
				break;

			directionVector.x = 0;
			directionVector.y = -1;
			break;
			
		case 'V':
			if ((direction == '^') || (direction == 'V'))
				break;

			directionVector.x = 0;
			directionVector.y = 1;
			break;

		case '>':
			if ((direction == '>') || (direction == '<'))
				break;

			directionVector.x = 1;
			directionVector.y = 0;
			break;
			
		case '<':
			if ((direction == '>') || (direction == '<'))
				break;

			directionVector.x = -1;
			directionVector.y = 0;
			break;
		}

		direction = newDirection;
	}
	
	public void eat(Food food) {
		addScore(food.getPoints());
	}

	// getters
	public int getLength() {
		return segments.size();
	}

	public LinkedList<Point> getSegments() {
		return segments;
	}

	public int getScore() {
		return score;
	}

	public String getName() {
		return name;
	}

	public char getDirection() {
		return direction;
	}
	public Point getHeadLocation() {
		return (Point) segments.peekLast().clone();
	}

}
