package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	
	private boolean[] keyDown = {false, false, false, false};
	private final int W = 0, S = 1, D = 2, A = 3;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ID.Player) {
				//KeyEvents for Player
				if (key == KeyEvent.VK_W) { tempObject.setVelY(-5);	keyDown[W] = true; }
				if (key == KeyEvent.VK_S) { tempObject.setVelY(5);	keyDown[S] = true; }				
				if (key == KeyEvent.VK_D) { tempObject.setVelX(5);	keyDown[D] = true; }				
				if (key == KeyEvent.VK_A) { tempObject.setVelX(-5);	keyDown[A] = true; }				
			}
		}
		
		if (key == KeyEvent.VK_ESCAPE) System.exit(0);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ID.Player) {
				//KeyEvents for Player
				if (key == KeyEvent.VK_W) keyDown[W] = false; 	
				if (key == KeyEvent.VK_S) keyDown[S] = false; 				
				if (key == KeyEvent.VK_D) keyDown[D] = false;			
				if (key == KeyEvent.VK_A) keyDown[A] = false; 
				
				//vertical movement
				if (!keyDown[W] && !keyDown[S]) tempObject.setVelY(0);
				
				//horizontal movement
				if (!keyDown[D] && !keyDown[A]) tempObject.setVelX(0);
			}
		}
	}

}
