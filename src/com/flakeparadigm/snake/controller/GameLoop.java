package com.flakeparadigm.snake.controller;

import javax.swing.JPanel;

import com.flakeparadigm.snake.model.ActiveFoods;
import com.flakeparadigm.snake.model.Food;
import com.flakeparadigm.snake.model.Player;

public class GameLoop implements Runnable {

	private final int FPS = 10;

	private long cycleTime;
	private boolean isRunning;
	private JPanel gamePanel;
	private Player player;
	private ActiveFoods foods;

	public GameLoop(JPanel playPanel, Player player, ActiveFoods foods) {
		gamePanel = playPanel;
		this.player = player;
		this.foods = foods;
		isRunning = true;
	}

	@Override
	public void run() {
		cycleTime = System.currentTimeMillis();

		while (isRunning) {
			updateGameState();
			updateGameView();
			syncFrameRate();
		}

	}

	private void updateGameState() {
		if (!player.movePlayer())
			isRunning = false;

		Food f = foods.checkNoms(player.getHeadLocation());
		if (f != null) {
			player.eat(f);
		}
	}

	private void updateGameView() {
		gamePanel.repaint();
	}

	private void syncFrameRate() {
		cycleTime += 1000 / FPS;
		long diff = cycleTime - System.currentTimeMillis();
		try {
			Thread.sleep(Math.max(0, diff));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
