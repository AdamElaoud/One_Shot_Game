package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 6691247796639148462L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running = false;

	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	
	public Game() {
		handler = new Handler();
		
		addKeyListener(new KeyInput(handler));

		new Window(WIDTH, HEIGHT, "Avoid the Enemies!", this);
		
		//adding HUD
		hud = new HUD();
		
		//adding spawner
		spawner = new Spawn(handler, hud);
		
		//adding Player
		handler.addObject(new Player(WIDTH / 2 - 16, HEIGHT / 2 - 32, ID.Player, handler));
		
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		//removes the need to click on window to use
		requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) /ns;
			lastTime = now;
			
			while(delta >= 1) {
				tick();
				delta--;
			}
			
			if(running) {
				render();
			}
			
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS " + frames);
				frames = 0;
			}
			
		}
		
		stop();
	}
	
	private void tick() {
		handler.tick();
		hud.tick();
		spawner.tick();
	}
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		
		if (bs == null) {
			//Loads 3 frames at a time for smoother viewing, more buffered frames decreases performance, too few and graphics will appear choppy
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		//set screen as black
		g.setColor(Color.black);
		//x and y coordinates are for displacement where top left corner is the origin
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		hud.render(g);
		
		// releases system resources being used
		g.dispose();
		// makes next available buffer visible
		bs.show();
	}
	
	public static float clamp(float var, float min, float max) {
		if (var >= max) 
			return var = max;
		else if (var <= min) 
			return var = min;
		else return var;
	}
	
	public static void main(String args[]) {
		new Game();
	}
}
