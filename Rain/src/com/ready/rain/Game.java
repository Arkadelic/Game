package com.ready.rain;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import java.io.File;

import com.ready.rain.graphics.Screen;
import com.ready.rain.input.Keyboard;
import com.ready.rain.input.Mouse;
import com.ready.rain.level.Level;
import com.ready.rain.level.Randomlevel;
import com.ready.rain.level.ReadLevel;
import com.ready.rain.level.SimplexNoise;
import com.ready.rain.mobs.Animation;
import com.ready.rain.entity.mib.Character;
import com.ready.rain.entity.mib.PP;
import com.ready.rain.graphics.LoadImages;
import com.ready.rain.WriteFile;


public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L; 

	public  Image img = null;
	private static double count1 = 1;
	private static int width = 300;  //4800
	private static int height = width / 16 * 9;
	private static int scale = 4;
	public static  String title = "Rain";	
	private static int rand = (int) ImageWriter.getRand();
	private static WriteFile WriteFile;
	public static boolean SideButton = false;
	public static boolean SideButton1 = false;
	public static boolean SideButton2 = false;
	public static boolean SideButton3 = false;
	public static boolean loaded = false;
	public static boolean menu = false; 
	public static boolean WorldS = false;
	public static boolean LevelSelect = false;
	public static boolean Playing = false;
	public static boolean Renaming = false;
	public static boolean Deleting = false;
	
	public static double count;
	public boolean dir = false;

	private Thread thread;	
	private JFrame frame;
	private Keyboard key;
	private boolean running = false;
	public static boolean facing = false;
	public int MouseX = Mouse.getX();
	public int MouseY = Mouse.getY();
	public int MouseB = Mouse.getButton();
	public int click;
	public int wait = 0;
	
	public int data1 = 10;
	public int data2 = 0;
	public int data3 = 0;
	public int data4 = 0;
	
	public int select;
	
	public BufferedImage image1;
	public BufferedImage image2;
	public BufferedImage image3;
	public BufferedImage image4;
	public BufferedImage image5;
	public BufferedImage image6;
	public BufferedImage image7;
	public BufferedImage image8;
	public BufferedImage image9;
	public BufferedImage image10;
	public BufferedImage Selectworld;
	public BufferedImage Play;
	public BufferedImage Delete;
	public BufferedImage Rename;
	public BufferedImage PlayB;
	public BufferedImage DeleteB;
	public BufferedImage RenameB;
	public BufferedImage Menu;
	public BufferedImage Start;
	public BufferedImage Help;
	public BufferedImage QuitB;
	public BufferedImage MenuB;
	public BufferedImage StartB;
	public BufferedImage HelpB;
	public BufferedImage Quit;
	public BufferedImage loading;
	public BufferedImage loading1;
	public BufferedImage loading2;
	public BufferedImage loading3;
	public BufferedImage loadingscrn;
	public BufferedImage WorldScrn;
	public BufferedImage WorldQuit;
	
	private Screen screen;
	private Animation animation;
	private Character character;
	private PP player;
	private LoadImages load;
	public static Level level;
	
	
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int [] pixels = ((DataBufferInt)image.getRaster() .getDataBuffer()) .getData();
	
	 static int xResolution=2500;
	 static int yResolution=2500;
     
	 static double[][] result=new double[xResolution][yResolution];
	 static double[][] result2=new double[xResolution][yResolution];	
	 static double[][] result3=new double[xResolution][yResolution];
	 
	public Game () {
		Dimension size = new Dimension (width*scale, height*scale);
		setPreferredSize (size);
		load = new LoadImages();
		screen = new Screen (width, height);
		animation = new Animation();
		frame = new JFrame() ;
		key = new Keyboard();
		Mouse mouse = new Mouse();
		addKeyListener (key);	
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		image1 = load.LoadImage("/textures/leo_righttorso.png");
		image2 = load.LoadImage("/textures/leo_lefttorso.png");
		image3 = load.LoadImage("/textures/leo_righthand.png");
		image4 = load.LoadImage("/textures/leo_lefthand.png");
		image5 = load.LoadImage("/textures/leo_rightleg.png");
		image6 = load.LoadImage("/textures/leo_leftleg.png");
		image7 = load.LoadImage("/textures/leo_righthead.png");
		image8 = load.LoadImage("/textures/leo_lefthead.png");
		image9 = load.LoadImage("/textures/pistol.png");
		image10 = load.LoadImage("/textures/pistol2.png");
		loading = load.LoadImage("/textures/loading.png");
		loading1 = load.LoadImage("/textures/loading1.png");
		loading2 = load.LoadImage("/textures/loading2.png");
		loading3 = load.LoadImage("/textures/loading3.png");
		loadingscrn = load.LoadImage("/textures/loading.png");
		Menu = load.LoadImage("/textures/menu.png");
		Start = load.LoadImage("/textures/Button/Startbtn.png");
		Help = load.LoadImage("/textures/Button/helpbtn.png");
		Quit = load.LoadImage("/textures/Button/quitbtn.png");
		StartB = load.LoadImage("/textures/Button/StartbtnB.png");
		HelpB = load.LoadImage("/textures/Button/helpbtnB.png");
		QuitB = load.LoadImage("/textures/Button/quitbtnB.png");
		WorldScrn = load.LoadImage("/textures/GameSavescrn.png");
		WorldQuit = load.LoadImage("/textures/Button/ReturntoMen.png");
		Play = load.LoadImage("/textures/Button/Play.png");
		PlayB = load.LoadImage("/textures/Button/PlayB.png");
		Rename = load.LoadImage("/textures/Button/Rename.png");
		RenameB = load.LoadImage("/textures/Button/RenameB.png");
		Delete = load.LoadImage("/textures/Button/Delete.png");
		DeleteB = load.LoadImage("/textures/Button/DeleteB.png");
		Selectworld = load.LoadImage("/textures/WorldSelect.png");
		
		level = new ReadLevel("/textures/WorldGen/Blank.png" );
		player = new PP (1180*16,1269*16,key);
		player.init (level);
		//player.x = 1250*16;
		//player.y = 1250*16;
	
		character = new Character (width*scale/2,height*scale/2,image1,image2,image3,image4,image5,image6,image7,image8,image9,image10,0,10,11,-16,44,10,11,-16,35,68,14,12,17,69,20,0,12,21,0,0);
	}
	public static int getScreenWidth(){
		return width;
	}
	public static int getScreenHeight(){
		return height;
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

	public void update () {
		key.update();
		if(loaded){
			character.update();
			player.update();
			if (key.left)facing = true;
			if (key.right)facing = false;		
		}
		else{
			if(dir){
				count --;
			}else{
				count++;
			}
			if(count <= 0){
				dir = false;
			}
			if(count >= 180){
				dir = true;
			}


			}

		
		if(LevelSelect){
			
			LevelSelect = false;
			
			SimplexNoise simplexNoise=new SimplexNoise(100,0.1,5000);
			
		    double xStart=Math.random()*10000;
		    double XEnd=xStart+2500/1.25;
		    double yStart=xStart;
		    double yEnd=XEnd;

		    for(int i=0;i<xResolution;i++){
		        for(int j=0;j<yResolution;j++){
		            int x=(int)(xStart+i*((XEnd-xStart)/xResolution));
		            int y=(int)(yStart+j*((yEnd-yStart)/yResolution));
		            result[i][j]=0.5*(1+simplexNoise.getNoise(x,y));
		        }
		    }
		    double xStart2=Math.random()*10000;
		    double XEnd2=xStart2+2500/1.25;
		    double yStart2=xStart2;
		    double yEnd2=XEnd2;

		    int xResolution2=xResolution;
		    int yResolution2=yResolution;



		    for(int i=0;i<xResolution2;i++){
		        for(int j=0;j<yResolution2;j++){
		            int x=(int)(xStart2+i*((XEnd2-xStart2)/xResolution2));
		            int y=(int)(yStart2+j*((yEnd2-yStart2)/yResolution2));
		            result2[i][j]=0.5*(1+simplexNoise.getNoise(x,y));
		        }
		    }
		    
		    
		    double xStart3=Math.random()*10000;
		    double XEnd3=xStart3+2500/1.25;
		    double yStart3=xStart3;
		    double yEnd3=XEnd3;

		    int xResolution3=xResolution;
		    int yResolution3=yResolution;



		    for(int i=0;i<xResolution3;i++){
		        for(int j=0;j<yResolution3;j++){
		            int x=(int)(xStart3+i*((XEnd3-xStart3)/xResolution3));
		            int y=(int)(yStart3+j*((yEnd3-yStart3)/yResolution3));
		            result3[i][j]=0.5*(1+simplexNoise.getNoise(x,y));
		        }
		    }
			
			ImageWriter.greyWriteImage(result,result2,result3);
			if(loaded){
			//Game.level = new ReadLevel("/textures/world" + (int)ImageWriter.getRand() + ".png" );
				if(data1 == -1){
					data1 = (int)ImageWriter.getRand();
				}else if(data2 == -1){
					data2 = (int)ImageWriter.getRand();
				}else if(data3 == -1){
					data3 = (int)ImageWriter.getRand();
				}else if(data4 == -1){
					data4 = (int)ImageWriter.getRand();
				}
				loaded = false;
			}
			

			
		}
		if(click == 1 && WorldS == false){
		if(Mouse.getX() > 512 && Mouse.getX() < 615 && Mouse.getY() > 144 && Mouse.getY() < 168){
			System.out.println(menu);
			//menu = true;
			WorldS = true;
			System.out.println(menu);			
		//	ImageWriter.greyWriteImage(result,result2,result3);
		}
		}
		

if(MouseX > 325 && MouseX < 811 && MouseY > 75 && MouseY < 175 && data1 > 0 && click == 1 && select == 1){
	System.out.println("loading...");
	//Game.level = new ReadLevel("/textures/WorldGen/world" + data1 + ".png" );
	level = new ReadLevel("/textures/WorldGen/world" + data1 + ".png");
	loaded = true;
	LevelSelect = false;
	WorldS = false;
	menu = true;
	select = 0;
}

if(MouseX > 325 && MouseX < 811 && MouseY > 218 && MouseY < 318 && data2 > 0 && click == 1&&select == 2){
	System.out.println("loading...");
	level = new ReadLevel("/textures/WorldGen/world" + data2 + ".png");
	loaded = true;
	LevelSelect = false;
	WorldS = false;
	menu = true;
	select = 0;
}
if(MouseX > 325 && MouseX < 811 && MouseY > 361 && MouseY < 461 && data3 > 0 && click == 1&&select == 3){
	System.out.println("loading...");
	level = new ReadLevel("/textures/WorldGen/world" + data3 + ".png");
	loaded = true;
	LevelSelect = false;
	WorldS = false;
	menu = true;
	select = 0;
}
if(MouseX > 325 && MouseX < 811 && MouseY > 504 && MouseY < 604 && data4 > 0 && click == 1&&select == 4){
	System.out.println("loading...");
	level = new ReadLevel("/textures/WorldGen/world" + data4 + ".png");
	loaded = true;
	LevelSelect = false;
	WorldS = false;
	menu = true;
	select = 0;
}
if(MouseX > 325 && MouseX < 811 && MouseY	 > 75 && MouseY < 175 && data1 > 0 && click == 1 && select != 1 && menu == false){
	SideButton = true;
	select = 1;
}
if(MouseX > 325 && MouseX < 811 && MouseY	 > 218 && MouseY < 318 && data2 > 0 && click == 1 && select != 2 && menu == false){
	SideButton1 = true;
	select = 2;
}
if(MouseX > 325 && MouseX < 811 && MouseY	 > 361 && MouseY < 461 && data3 > 0 && click == 1 && select != 3 && menu == false){
	SideButton2 = true;
	select = 3;
}
if(MouseX > 325 && MouseX < 811 && MouseY	 > 504 && MouseY < 604 && data4 > 0 && click == 1 && select != 4 && menu == false){
	SideButton3 = true;
	select = 4;
}
		
		click = 0;
		if(MouseB == 1 && Mouse.getButton() == -1){
			click = 1;
		}
		MouseB = Mouse.getButton();
		MouseY = Mouse.getY();
		MouseX = Mouse.getX();
	

	}

	
	

	public void render () {

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy (3) ;
			return;
		}
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.drawImage (image, 0, 0, getWidth (), getHeight(), null);
		screen.clear();
		g.setColor(Color.WHITE);
		g.setFont(new Font("Times New Roman", 0, 25));
		if(menu == false){
		g.drawString("Mouse x: " + MouseX + ",  Mouse y: " + MouseY + ", clickValue:" + click, 20 , 20);
		}
		if (menu == false){
			
			g.drawImage (Menu,getWidth ()/2 - Menu.getWidth()/2 , getHeight()/10 - Menu.getHeight()/2, null);	
			g.drawImage (Start,getWidth ()/2 - Start.getWidth()/2 - 35, getHeight()/5 - Start.getHeight()/2 + 25, null);
			g.drawImage (Help,getWidth ()/2 - Help.getWidth()/2 - 38, getHeight()/4 - Help.getHeight()/2 + 50, null);
			g.drawImage (Quit,getWidth ()/2 - Quit.getWidth()/2 -50, getHeight()/3 - Quit.getHeight()/2 + 50, null);
			g.fillRect(Mouse.getX() - 10/2, Mouse.getY() -10 /2, 10, 10);
if(click == 1 && WorldS == false){
			if(Mouse.getX() > 512 && Mouse.getX() < 615 && Mouse.getY() > 250 && Mouse.getY() < 270){
				System.exit(0);
				
			}
}

if (WorldS){
	g.drawImage (WorldScrn,getWidth ()/2 - WorldScrn.getWidth()/2 - 38, getHeight()/4 - WorldScrn.getHeight()/2 + 160, null);
	g.drawImage (WorldQuit,getWidth ()/2 - WorldQuit.getWidth()/2 - 470, getHeight()/4 - WorldScrn.getHeight()/2 + 160, null);

	
	if(select == 1){
		g.drawImage(Selectworld, 261,33 + Selectworld.getHeight()*0,null);
		g.drawImage (Play,getWidth ()/2 - Play.getWidth()/2 + 400, 30, null);
		g.drawImage (Delete,getWidth ()/2 - Delete.getWidth()/2 + 400, 80, null);
		g.drawImage (Rename,getWidth ()/2 - Rename.getWidth()/2 + 400, 125, null);
		}
	if(select == 2){
		g.drawImage(Selectworld, 261,33 + Selectworld.getHeight()*1,null);
		g.drawImage (Play,getWidth ()/2 - Play.getWidth()/2 + 400, 175, null);
		g.drawImage (Delete,getWidth ()/2 - Delete.getWidth()/2 + 400, 225, null);
		g.drawImage (Rename,getWidth ()/2 - Rename.getWidth()/2 + 400, 275, null);
	 
		}
	if(select == 3){
		g.drawImage(Selectworld, 261,33  + Selectworld.getHeight()*2,null);
		g.drawImage (Play,getWidth ()/2 - Play.getWidth()/2 + 400, 335, null);
		g.drawImage (Delete,getWidth ()/2 - Delete.getWidth()/2 + 400, 365, null);
		g.drawImage (Rename,getWidth ()/2 - Rename.getWidth()/2 + 400, 415, null);
	 }
	if(select == 4){
		g.drawImage(Selectworld, 261,33 + Selectworld.getHeight()*3,null);
		g.drawImage (Play,getWidth ()/2 - Play.getWidth()/2 + 400, 485, null);
		g.drawImage (Delete,getWidth ()/2 - Delete.getWidth()/2 + 400, 515, null);
		g.drawImage (Rename,getWidth ()/2 - Rename.getWidth()/2 + 400, 560, null);
 
		}
	if(data1 == 0){
		g.drawString("New World ", 506 , 137);
	}
	if(data2 == 0){
		g.drawString("New World ", 506 , 280);
	}
	if(data3 == 0){
		g.drawString("New World ", 506 , 423);
	}
	if(data4 == 0){
		g.drawString("New World", 506, 566);
	}
	if(data1 == -1){
		g.drawString("Loading...", 506 , 137);
	}
	if(data2 == -1){
		g.drawString("Loading...", 506 , 280);
	}
	if(data3 == -1){
		g.drawString("Loading...", 506 , 423);
	}
	if(data4 == -1){
		g.drawString("Loading...", 506, 566);
	}
	if(data1 > 0){
		g.drawString("World " + data1, 518 , 137);
	}
	if(data2 > 0){
		g.drawString("World " + data2, 518 , 280);
	}
	if(data3 > 0){
		g.drawString("World " + data3, 518 , 423);
	}
	if(data4 > 0){
		g.drawString("World " + data4, 518 , 566);
	}

}
if(click == 1 && WorldS){
	if(Mouse.getX() > 11 && Mouse.getX() < 250 && Mouse.getY() > 50 && Mouse.getY() < 70){
		WorldS = false;
		select = 0;
	}
	if(MouseX > 325 && MouseX < 811 && MouseY	 > 75 && MouseY < 175 && data1 == 0){
		LevelSelect =true;
		data1 = -1;
		select = 0;

	}
	if(MouseX > 325 && MouseX < 811 && MouseY	 > 218 && MouseY < 318 && data2 == 0){
		LevelSelect =true;
		data2 = -1;
		select = 0;
	}
	if(MouseX > 325 && MouseX < 811 && MouseY	 > 361 && MouseY < 461 && data3 == 0){
		LevelSelect =true;
		data3 = -1;
		select = 0;
	}
	if(MouseX > 325 && MouseX < 811 && MouseY	 > 504 && MouseY < 604 && data4 == 0){
		LevelSelect =true;
		data4 = -1;
		select = 0;
	}



}
if(WorldS == false){
if(Mouse.getX() > 512 && Mouse.getX() < 615 && Mouse.getY() > 144 && Mouse.getY() < 168){
	g.drawImage (StartB,getWidth ()/2 - StartB.getWidth()/2 - 35, getHeight()/5 - StartB.getHeight()/2 + 25, null);
}
if(Mouse.getX() > 512 && Mouse.getX() < 615 && Mouse.getY() > 250 && Mouse.getY() < 270){
	g.drawImage (QuitB,getWidth ()/2 - QuitB.getWidth()/2 -50, getHeight()/3 - QuitB.getHeight()/2 + 50, null);
}
}


	}
		else if(loaded){
			screen.clear();
		level.render(player.x, player.y, screen);
		
	
		for (int i = 0; i < pixels.length; i++) {
			pixels [i] = screen.pixels [i];
		}	
		
		character.targetx = Mouse.getX();
		character.targety = Mouse.getY();
		if(key.up || key.down || key.right || key.left){
			animation.AnimateLegs(bs,character,true,facing);
		}else{
			animation.AnimateLegs(bs,character,false,facing);
			
		}
		g.setColor(Color.WHITE);
		g.setFont(new Font("Times New Roman", 0, 25));
		g.drawString("X: " + player.x / 16  + ", Y: " +  player.y / 16 , 0, 18);
		//g.drawString("Character xa " + character.xa + ",  Character ya: " + character.ya , 20 , 100);
		
		}else{
			g.drawImage (loadingscrn,getWidth ()/2 - loadingscrn.getWidth()/2, getHeight()/2 - loadingscrn.getHeight()/2, null);
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
	System.out.println(Game.menu);
	
	

		
		System.out.println(Game.menu);

	  if(Game.menu){
			    
	    WriteFile = new WriteFile("WorldSave.txt");
	  }
	

	



    
    if(loaded){
System.out.println("world" + (int)ImageWriter.getRand() + ".png");    	
       // Game.level = new ReadLevel("/textures/world" + (int)ImageWriter.getRand() + ".png" );
    }

}





}
