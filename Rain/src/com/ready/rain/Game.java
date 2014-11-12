package com.ready.rain;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.ready.rain.graphics.Screen;
import com.ready.rain.input.Keyboard;
import com.ready.rain.level.Level;
import com.ready.rain.level.Randomlevel;
import com.ready.rain.mobs.Animation;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public  Image img = null;
	
	public static int width = 300;
	public static int height = width / 16 * 9;
	public static int scale = 4;
	public static  String title = "Rain";	
	
	private Level level;
	private Thread thread;	
	private JFrame frame;
	private Keyboard key;
	private boolean running = false;
	private boolean facing = false;
	
	private Screen screen;
	private Animation animation;
	
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int [] pixels = ((DataBufferInt)image.getRaster() .getDataBuffer()) .getData();
		
	
	public Game () {
		Dimension size = new Dimension (width*scale, height*scale);
		setPreferredSize (size);
		
		screen = new Screen (width, height);
		animation = new Animation();
		frame = new JFrame() ;
		key = new Keyboard();
		addKeyListener (key);	
		level = new Randomlevel(256, 256);
		

	}
	public synchronized void start () {
		running = true;
		thread = new Thread (this, "Display"); 
		thread.start();
		animation.LoadImage();
		
	}
	public synchronized void stop () {
		running = false;
		try {
		thread.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {	
		long LastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime () ; 
			delta += (now - LastTime) / ns;
			LastTime = now;
			while (delta >= 1) {
				update ();
				updates +=1;
				delta--;
			}
			render () ;
			frames ++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + "ups, " + frames + "fps");
				frame.setTitle(title + "   |   "   + updates + "ups, " + frames + "fps");
				updates = 0;
				frames = 0;
			}
		}
	stop () ;
	}
	int x = 0, y = 0;
	public void update () {
		key.update();
		if (key.up) y--;
		if (key.down) y++;
		if (key.left){
			x--;
			facing = true;
		}
		if (key.right){
			x++; 
			facing = false;
		}

	}
	public void render () {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy (3) ;
			return;
		}
		screen.clear();
		level.render(x, y, screen);
		
		for (int i = 0; i < pixels.length; i++) {
			pixels [i] = screen.pixels [i];
		}
		
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.drawImage (image, 0, 0, getWidth (), getHeight(), null);
		
		if(key.up || key.down || key.right || key.left){
			animation.AnimateLegs(bs,400,400,true,facing);
		}else{
			animation.AnimateLegs(bs,400,400,false,facing);
		}
		
		g.dispose();
		bs.show();
		
	
		
	}
	
public static void main (String[] args){
	Game game = new Game ();
	game.frame.setResizable (false);
	game.frame.setTitle (Game.title);
	game.frame.add(game);
	game.frame.pack();
	game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	game.frame.setLocationRelativeTo(null);
	game.frame.setVisible(true);
	
	game.start();
}
}
