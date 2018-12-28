package main;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Purpose of class is to create window to house game
 * @author adame
 *
 */
public class Window extends Canvas {

	private static final long serialVersionUID = -4810618286807932601L;

	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);
		
		//set up frame size
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		//makes frame generate in middle of screen
		frame.setLocationRelativeTo(null);
		
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}
}
