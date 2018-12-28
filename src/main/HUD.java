package main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public static float HEALTH = 100;
	private float greenValue = 255;
	
	private int score = 0, level = 1;
	
	public void tick() {
		HEALTH = Game.clamp(HEALTH, 0, 100);
		
		greenValue = Game.clamp(greenValue, 0, 255);
		greenValue = HEALTH * 2;
		
		score++;
	}
	
	public void render(Graphics g) {
		//Background
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		
		//Health 	   Color(red, green, blue)
		g.setColor(new Color(75, (int)greenValue, 0));
		g.fillRect(15, 15, (int)HEALTH * 2, 32);
		
		//border
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
		
		g.drawString("Score: " + score, 15, 64);
		g.drawString("Level: " + level, 15, 80);
	}
	
	public void score(int score) {
		this.score = score;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getLevel() {
		return level;
	}

}
