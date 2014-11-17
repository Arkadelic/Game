package com.ready.rain.mobs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.ready.rain.entity.mib.Player;
import com.ready.rain.Game;


public class Animation extends JPanel {
	boolean dir = true;
	boolean prev = false;
	double rot = 0;
	BufferedImage image7;
	BufferedImage image1;
	BufferedImage image2;
	BufferedImage image3;
	BufferedImage image4;
	BufferedImage image5;
	BufferedImage image6;
	BufferedImage img3;
	BufferedImage img1;
	BufferedImage img2;
	BufferedImage img4;


	public void AnimateLegs(BufferStrategy bs, Player player, boolean moving,boolean facing){
		
		int x = player.x;
		int y = player.y;
		int xjoint1 = player.xjoint1;
		int xjoint2 = player.xjoint2;
		int yjoint1 = player.yjoint1;
		int yjoint2 = player.yjoint2;
		int xjoint3 = player.xjoint3;
		int xjoint4 = player.xjoint4;
		int yjoint3 = player.yjoint3;
		int yjoint4 = player.yjoint4;
			
		if(facing != prev){
			dir = true;
			rot = 0;
			prev = facing;
		}
		

		
		if (moving == true){
		if(rot >= 50){
			dir = true;
		}
		if(rot <= -50){
			dir = false;
		}
		if(dir == false){
			rot++;
		}else{
			rot--;
		}
		}else{
			if(rot >= 0)rot -= 0.5;
			if(rot <= 0)rot += 0.5;
			
		}
		if (facing == true){
			img1 = image2;
			img2 = player.lefttorso;
			img3 = player.leftarm;
		}else{	
			img3 = player.rightarm;
			img1 = image1;
			img2 = player.righttorso;
		
		}
		//img3 = image5;
		//img4 = image6;
		
		
		
		Graphics2D g4 = (Graphics2D) bs.getDrawGraphics();
		
		if(facing == true){
			g4.translate(x + xjoint1, y - yjoint1);
			g4.rotate(Math.toRadians(-rot));
			g4.translate(-x - xjoint1, -y + yjoint1);
			g4.drawImage(img3, x + xjoint1 - xjoint2, y - yjoint1 + yjoint2, null);
		
		}else{
			xjoint1 += player.lefttorso.getWidth();
			g4.translate(x + xjoint1, y - yjoint1);
			g4.rotate(Math.toRadians(-rot));
			g4.translate(-x - xjoint1, -y + yjoint1);
			g4.drawImage(img3, x + xjoint1 -2 * xjoint2, y - yjoint1 + yjoint2, null);
		}

		g4.dispose();
		
		
		Graphics2D g1 = (Graphics2D) bs.getDrawGraphics();
		
		g1.translate(x + 16 + 25, y - 5 - 10);
		g1.rotate(Math.toRadians(rot));
		g1.translate(-x - 16 - 25, -y + 5 + 10);
		g1.drawImage(img1, x + 25, y - 10, null);
	
		g1.dispose();
		
	Graphics2D g2 = (Graphics2D) bs.getDrawGraphics();
		
		g2.translate(x + 16, y - 5);
		g2.rotate(Math.toRadians(-rot));
		g2.translate(-x - 16, -y + 5);
		g2.drawImage(img1, x - 3, y - 10, null);
	
		g2.dispose();
	

	

	
	Graphics2D g3 = (Graphics2D) bs.getDrawGraphics();

	g3.drawImage(img2, x, y - 70, null);

	g3.dispose();
	
	Graphics2D g5 = (Graphics2D) bs.getDrawGraphics();
	
	if(facing == true){
		g5.translate(x + xjoint3, y - yjoint3);
		g5.rotate(Math.toRadians(rot));
		g5.translate(-x - xjoint3, -y + yjoint3);
		g5.drawImage(img3, x + xjoint3 - xjoint4, y - yjoint3 + yjoint4, null);
	
	}else{
		xjoint3 -= player.lefttorso.getWidth() - 10;
		g5.translate(x + xjoint3, y - yjoint3);
		g5.rotate(Math.toRadians(rot));
		g5.translate(-x - xjoint3, -y + yjoint3);
		g5.drawImage(img3, x + xjoint3 -2 * xjoint4, y - yjoint3 + yjoint4, null);
	}
	g5.dispose();
	
}

	

}
