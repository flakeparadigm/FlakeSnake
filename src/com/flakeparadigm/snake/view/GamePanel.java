package com.flakeparadigm.snake.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.LinkedList;

import javax.swing.JPanel;

import com.flakeparadigm.snake.model.ActiveFoods;
import com.flakeparadigm.snake.model.Food;
import com.flakeparadigm.snake.model.ImageBuffer;
import com.flakeparadigm.snake.model.Level;
import com.flakeparadigm.snake.model.Player;

public class GamePanel extends JPanel {

	// Constants
	private final int BLOCK_SIZE = Level.BLOCK_SIZE;

	private Player player;
	private LinkedList<Point> playerSegments;
	private ActiveFoods foods;

	// Images
	private Image playerSegment;

	public GamePanel(Player player, ActiveFoods foods) {
		this.player = player;
		this.foods = foods;

		playerSegments = player.getSegments();
		playerSegment = ImageBuffer.getImage("snake");
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		for (Point s : playerSegments) {
			g2.drawImage(playerSegment, s.x * BLOCK_SIZE, s.y * BLOCK_SIZE,
					null);
		}

		for (Food f : foods) {
			g2.drawImage(f.getImg(), f.getLocation().x * BLOCK_SIZE,
					f.getLocation().y * BLOCK_SIZE, null);
		}

	}
}
