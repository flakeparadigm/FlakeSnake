package com.flakeparadigm.snake.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.flakeparadigm.snake.controller.GameLoop;
import com.flakeparadigm.snake.model.ActiveFoods;
import com.flakeparadigm.snake.model.Level;
import com.flakeparadigm.snake.model.Player;

public class Game extends JFrame {

	// constants
	private final String GAME_TITLE = "FlakeSnake";
	private final int WINDOW_WIDTH = 500;
	private final int WINDOW_HEIGHT = 600;
	private Dimension contentSize;
	
	// controllers
	GameLoop loop;
	Thread gameThread;
	
	// views
	JPanel levelPanel;
	JPanel playPanel;
	
	// models
	Level level;
	Player player;
	ActiveFoods foods;
	
	public Game() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setTitle(GAME_TITLE);
		//this.setLayout(null);
		
		level = new Level(1);
		player = new Player("Player", level);
		foods = new ActiveFoods(level);
		
		contentSize = level.getDisplaySize();
		
		playPanel = new GamePanel(player, foods);
		this.add(playPanel);
		playPanel.setLocation(0,0);
		playPanel.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		playPanel.setOpaque(false);

		levelPanel = new LevelPanel(level);
		levelPanel.setLocation(0,0);
		levelPanel.setPreferredSize( contentSize );
		this.add(levelPanel);
		
		registerListeners();
		
		this.pack();
		this.setVisible(true);
		
		loop = new GameLoop(playPanel, player, foods);
		gameThread = new Thread(loop);
		gameThread.start();
		
	}
	
	private void registerListeners() {
		InputMap im = this.getRootPane().getInputMap();
		ActionMap am = this.getRootPane().getActionMap();
		
		// up movement
		// create movement keystrokes
		KeyStroke upArrow = KeyStroke.getKeyStroke("UP");
		KeyStroke upW =  KeyStroke.getKeyStroke("W");
		// add keystrokes to the input map
		im.put(upArrow, "goUp");
		im.put(upW, "goUp");
		// ad the actions to the action map
		am.put("goUp", new AbstractAction() {
			// create an action to run the function we need
			@Override
			public void actionPerformed(ActionEvent e) {
				goUp();
			}
		});
		
		// right movement
		KeyStroke rightArrow = KeyStroke.getKeyStroke("RIGHT");
		KeyStroke rightD =  KeyStroke.getKeyStroke("D");
		im.put(rightArrow, "goRight");
		im.put(rightD, "goRight");
		am.put("goRight", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goRight();
			}
		});
		
		// down movement
		KeyStroke downArrow = KeyStroke.getKeyStroke("DOWN");
		KeyStroke downS =  KeyStroke.getKeyStroke("S");
		im.put(downArrow, "goDown");
		im.put(downS, "goDown");
		am.put("goDown", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goDown();
			}
		});
		
		// left movement
		KeyStroke leftArrow = KeyStroke.getKeyStroke("LEFT");
		KeyStroke leftA =  KeyStroke.getKeyStroke("A");
		im.put(leftArrow, "goLeft");
		im.put(leftA, "goLeft");
		am.put("goLeft", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goLeft();
			}
		});
		
	}

	private void goUp() {
		player.setDirection('^');
	}
	private void goRight() {
		player.setDirection('>');
	}
	private void goDown() {
		player.setDirection('V');
	}
	private void goLeft() {
		player.setDirection('<');
	}
	
	
	public static void main(String[] args) {
		Game g = new Game();
	}
}
