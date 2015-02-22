package com.flakeparadigm.snake.model;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/*
 * CLASS: Level
 * This class creates and holds the array that represents the level.
 * 
 * CONSTRUCTOR: takes the level number (integer) and reads that level from the
 * levels directory.
 * 
 * LEVEL FILES: level files consist of three main parts. The first two are the
 * width and height of the map, respectively. The third is the actual layout of
 * the map, where "X" marks solid wall, the starting point and direction is 
 * specified by one of (^, >, V, <), and any other character marks a navigable
 * space. If the specified dimensions don't match the layout of the level, the
 * remaining space will be deemed navigable. If no starting point is specified,
 * the middle will be used.
 * 
 * LEVEL ARRAY: a block that is navigable will contain the value "true", and one
 * that is not will contain the value of "false".
 */

public class Level {

	private int mapHeight = 60;
	private int mapWidth = 50;
	boolean[][] map;

	private Point startPoint = new Point();
	private char startDirection = '0';
	
	public static final int BLOCK_SIZE = 10;

	public Level(int levelNum) {
		String fileString = "levels/level" + levelNum + ".txt";
		Scanner levelScanner;

		// load level file
		try {
			levelScanner = new Scanner(new File(fileString));

			mapWidth = Integer.parseInt(levelScanner.nextLine());
			mapHeight = Integer.parseInt(levelScanner.nextLine());
			makeMapScanner(levelScanner);

		} catch (IOException e) {
			// issue loading the file
			System.out.println("File not found: " + fileString);
			System.out.println("Using default, empty level.");
			e.printStackTrace();

			// use blank level
			makeBlankMap();

		} catch (Exception e) {
			// any other issues
			System.out.println("Formatting error: " + fileString);
			System.out.println("Using default, empty level.");

			// use blank level
			makeBlankMap();
		}
		
		// check if a starting point was set. Will have direction if it
		// has been set before.
		if( startDirection == '0' ) {
			startPoint.y = mapHeight/2;
			startPoint.x = mapWidth/2;
			startDirection = '^';
		}

	}

	// making an empty map in case of issues
	private void makeBlankMap() {
		// define the map to be the default specified height
		map = new boolean[mapHeight][mapWidth];

		// fill with blank spaces
		for (int i = 0; i < mapHeight; i++) {
			for (int j = 0; j < mapWidth; j++) {
				map[i][j] = true;
			}
		}
	}

	// make the map from the scanned file
	private void makeMapScanner(Scanner file) {
		String line;
		map = new boolean[mapHeight][mapWidth];

		for (int i = 0; i < mapHeight; i++) {
			if (file.hasNext()) {
				// read next line from file to fill in array
				line = file.nextLine();
				int lineLength = line.length();

				for (int j = 0; j < mapWidth; j++) {
					// check if we have some string left
					if (j >= lineLength) {
						// if not, fill with blank space
						map[i][j] = true;
						
					} else if (line.charAt(j) == 'X') {
						map[i][j] = false;
						
					} else {
						char ch = line.charAt(j);

						// if thecharacter 
						switch (ch) {
						case '^':
						case '>':
						case 'V':
						case 'v':
						case '<':
							startPoint.y = i;
							startPoint.x = j;
							startDirection = Character.toUpperCase(ch);
						default:
							map[i][j] = true;
							break;
						}
					}
				}
			} else {
				// fill in with space if end of file reached
				for (int j = 0; j < mapWidth; j++) {
					map[i][j] = true;
				}
			}
		}
	}
	
	// for viewing the map. useful for debug
	public void printArray() {
		for( int i=0; i < mapHeight; i++) {
			for( int j=0; j < mapWidth; j++) {
				if( map[i][j] ) {
					System.out.print('`');
				} else {
					System.out.print('X');
				}
			}
			System.out.print('\n');
		}
	}

	
	// getters
	public boolean[][] getMap() {
		boolean[][] mapCopy = new boolean[map.length][map[0].length];
		
		for( int i=0; i<map.length; i++) {
			for( int j=0; j<map[i].length; j++) {
				mapCopy[i][j] = map[i][j];
			}
		}
		
		return mapCopy;
	}
	public Dimension getMapSize() {
		return new Dimension(mapWidth, mapHeight);
	}
	public Dimension getDisplaySize() {
		return new Dimension(mapWidth*BLOCK_SIZE, mapHeight*BLOCK_SIZE);
	}
	public char getStartDirection() {
		return startDirection;
	}
	public Point getStartPoint() {
		return (Point) startPoint.clone();
	}
	
	// movement checkers
	public boolean canMoveTo(Point point) {
		int x = point.x;
		int y = point.y;
		
		try {
			// return if the location can be moved to
			return map[y][x];
			
		} catch (IndexOutOfBoundsException e) {
			// if the location doesn't exist, false
			return false;
		}
		
	}
}
