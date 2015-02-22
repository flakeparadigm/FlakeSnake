package com.flakeparadigm.snake.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

import com.flakeparadigm.snake.model.ImageBuffer;
import com.flakeparadigm.snake.model.Level;

public class LevelPanel extends JPanel {
	
	private final String WALL_TILE = "wall";
	private final int BLOCK_SIZE = Level.BLOCK_SIZE;
	
	// images
	private Image mapTile;
	
	private Level level;
	
	public LevelPanel(Level l) {
		this.level = l;
		mapTile = ImageBuffer.getImage(WALL_TILE);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		boolean[][] map = level.getMap();
		Dimension size = level.getMapSize();
		
		g2.setColor(new Color(0,75,85));
		g2.fillRect(0, 0, ((int) size.getWidth())*BLOCK_SIZE, ((int) size.getHeight())*BLOCK_SIZE);
		
		for(int i=0; i < map.length; i++) {
			for(int j=0; j < map[i].length; j++) {
				if(!map[i][j])
					g2.drawImage(mapTile, j*BLOCK_SIZE, i*BLOCK_SIZE, null);
			}
		}
	}
	
	public void update() {
		repaint();
	}
}
