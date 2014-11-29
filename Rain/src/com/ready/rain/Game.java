package com.ready.rain;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.io.File;

import com.ready.rain.graphics.Screen;
import com.ready.rain.input.Keyboard;
import com.ready.rain.level.Level;
import com.ready.rain.level.Randomlevel;
import com.ready.rain.level.ReadLevel;
import com.ready.rain.level.SimplexNoise;
import com.ready.rain.mobs.Animation;
import com.ready.rain.entity.mib.Character;
import com.ready.rain.graphics.LoadImages;
import com.ready.rain.WriteFile;


public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L; 

	public  Image img = null;
	
	public static int width = 300;
	public static int height = width / 16 * 9;
	public static int scale = 4;
	public static  String title = "Rain";	
	private static int rand = (int) ImageWriter.getRand();
	private static WriteFile WriteFile;
	public static boolean loaded = false;

	private Thread thread;	
	private JFrame frame;
	private Keyboard key;
	private boolean running = false;
	private boolean facing = false;
	
	public BufferedImage image1;
	public BufferedImage image2;
	public BufferedImage image3;
	public BufferedImage image4;
	public BufferedImage image5;
	public BufferedImage image6;
	public BufferedImage image7;
	public BufferedImage image8;
	
	private Screen screen;
	private Animation animation;
	private Character character;
	private LoadImages load;
	public static Level level;
	
	
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int [] pixels = ((DataBufferInt)image.getRaster() .getDataBuffer()) .getData();
		
	
	public Game () {
		Dimension size = new Dimension (width*scale, height*scale);
		setPreferredSize (size);
		load = new LoadImages();
		screen = new Screen (width, height);
		animation = new Animation();
		frame = new JFrame() ;
		key = new Keyboard();

		addKeyListener (key);	
	
		

		
		
		
		image1 = load.LoadImage("/textures/leo_righttorso.png");
		image2 = load.LoadImage("/textures/leo_lefttorso.png");
		image3 = load.LoadImage("/textures/leo_righthand.png");
		image4 = load.LoadImage("/textures/leo_lefthand.png");
		image5 = load.LoadImage("/textures/leo_rightleg.png");
		image6 = load.LoadImage("/textures/leo_leftleg.png");
		image7 = load.LoadImage("/textures/leo_righthead.png");
		image8 = load.LoadImage("/textures/leo_lefthead.png");

		character = new Character (width*scale/2,height*scale/2,image1,image2,image3,image4,image5,image6,image7,image8,0,10,11,-16,44,10,11,-16,35,68,14,12,17,69,20,0,12,21);
	}
	
	public synchronized void start () {
		running = true;
		thread = new Thread (this, "Display"); 
		thread.start();
		
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
	int x = (3000 * 16 / 2) , y = (3000 * 16 / 2);
	public void update () {
		key.update();
		if (key.up) 
			y-= 2.5;
		if (key.down) 
			y+=2.5;
		if (key.left){
			x-=2.5;
			facing = true;
		}
		if (key.right){
			x+=2.5; 
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
		if(loaded){
		level.render(x, y, screen);
		}
		for (int i = 0; i < pixels.length; i++) {
			pixels [i] = screen.pixels [i];
		}
		
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.drawImage (image, 0, 0, getWidth (), getHeight(), null);
		
		
		if(key.up || key.down || key.right || key.left){
			animation.AnimateLegs(bs,character,true,facing);
		}else{
			animation.AnimateLegs(bs,character,false,facing);
		}
		g.setColor(Color.WHITE);
		g.setFont(new Font("Times New Roman", 0, 25));
		g.drawString("X: " + x / 16 + ", Y: " + y / 16, 0, 18);
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
	

	SimplexNoise simplexNoise=new SimplexNoise(100,0.1,5000);



    double xStart=Math.random()*10000;
    double XEnd=xStart+2500;
    double yStart=xStart;
    double yEnd=XEnd;

    int xResolution=2500;
    int yResolution=2500;

    double[][] result=new double[xResolution][yResolution];

    for(int i=0;i<xResolution;i++){
        for(int j=0;j<yResolution;j++){
            int x=(int)(xStart+i*((XEnd-xStart)/xResolution));
            int y=(int)(yStart+j*((yEnd-yStart)/yResolution));
            result[i][j]=0.5*(1+simplexNoise.getNoise(x,y));
        }
    }
    double xStart2=Math.random()*10000;
    double XEnd2=xStart2+2500;
    double yStart2=xStart2;
    double yEnd2=XEnd2;

    int xResolution2=xResolution;
    int yResolution2=yResolution;

    double[][] result2=new double[xResolution2][yResolution2];

    for(int i=0;i<xResolution2;i++){
        for(int j=0;j<yResolution2;j++){
            int x=(int)(xStart2+i*((XEnd2-xStart2)/xResolution2));
            int y=(int)(yStart2+j*((yEnd2-yStart2)/yResolution2));
            result2[i][j]=0.5*(1+simplexNoise.getNoise(x,y));
        }
    }
    
    
    double xStart3=Math.random()*10000;
    double XEnd3=xStart3+2500;
    double yStart3=xStart3;
    double yEnd3=XEnd3;

    int xResolution3=xResolution;
    int yResolution3=yResolution;

    double[][] result3=new double[xResolution3][yResolution3];

    for(int i=0;i<xResolution3;i++){
        for(int j=0;j<yResolution3;j++){
            int x=(int)(xStart3+i*((XEnd3-xStart3)/xResolution3));
            int y=(int)(yStart3+j*((yEnd3-yStart3)/yResolution3));
            result3[i][j]=0.5*(1+simplexNoise.getNoise(x,y));
        }
    }

    ImageWriter.greyWriteImage(result,result2,result3);
    WriteFile = new WriteFile("WorldSave.png");

    
    if(loaded){
 //       Game.level = new ReadLevel("/textures/world9.png" );
    }
}





}
