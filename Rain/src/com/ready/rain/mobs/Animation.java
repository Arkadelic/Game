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

import com.ready.rain.entity.mib.Character;
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


	public void AnimateLegs(BufferStrategy bs, Character character, boolean moving,boolean facing){
		
		int x = character.x;
		int y = character.y;
		int xjoint1 = character.xjoint1;
		int xjoint2 = character.xjoint2;
		int yjoint1 = character.yjoint1;
		int yjoint2 = character.yjoint2;
		int xjoint3 = character.xjoint3;
		int xjoint4 = character.xjoint4;
		int yjoint3 = character.yjoint3;
		int yjoint4 = character.yjoint4;
		int xjoint5 = character.xjoint5;
		int xjoint6 = character.xjoint6;
		int yjoint5 = character.yjoint5;
		int yjoint6 = character.yjoint6;	
		int xjoint7 = character.xjoint7;
		int yjoint7 = character.yjoint7;
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
			img2 = character.lefttorso;
			img3 = character.leftarm;
			img4 = character.leftleg;
		}else{	
			img3 = character.rightarm;
			img1 = image1;
			img2 = character.righttorso;
			img4 = character.rightleg;
		
		}
		//img3 = image5;
		//img4 = image6;
		
		Graphics2D g6 = (Graphics2D) bs.getDrawGraphics();
		Graphics2D g7 = (Graphics2D) bs.getDrawGraphics();
		
		if(facing == true){
			g6.translate( x + xjoint5 - xjoint6 + xjoint6, y - yjoint5 + (yjoint5 - yjoint6) + yjoint6);
			g6.rotate(Math.toRadians(-rot));
			g6.translate(-(x + xjoint5 - xjoint6) - xjoint6,-( y - yjoint5 + (yjoint5 - yjoint6) )-yjoint6);
			g6.drawImage(img4, x + xjoint5 - xjoint6, y - yjoint5 + (yjoint5 - yjoint6), null);
			
			g7.translate( x + xjoint7 - xjoint6 + xjoint6, y - yjoint7 + (yjoint7 - yjoint6) + yjoint6);
			g7.rotate(Math.toRadians(rot));
			g7.translate(-(x + xjoint7 - xjoint6) - xjoint6,-( y - yjoint7 + (yjoint7 - yjoint6) )-yjoint6);
			g7.drawImage(img4, x + xjoint7 - xjoint6, y - yjoint7 + (yjoint7 - yjoint6), null);
		
		}else{
			xjoint6 = (character.lefttorso.getWidth() - xjoint6);
			
			g6.translate((x)+img2.getWidth()-xjoint5,(y - img2.getHeight() + yjoint5 - xjoint6));
			g6.rotate(Math.toRadians(rot));
			g6.translate(-((x)+img2.getWidth()-xjoint5),-(y - img2.getHeight() + yjoint5 - xjoint6));
			g6.drawImage(img4, x + xjoint5 - xjoint6, y - yjoint5 + (yjoint5 - yjoint6), null);
			
			g7.translate((x)+img2.getWidth()-xjoint7,(y - img2.getHeight() + yjoint7 - xjoint6));
			g7.rotate(Math.toRadians(-rot));
			g7.translate(-((x)+img2.getWidth()-xjoint7),-(y - img2.getHeight() + yjoint7 - xjoint6));
			g7.drawImage(img4, x + xjoint7 - xjoint6, y - yjoint7 + (yjoint7 - yjoint6), null);
		}
		g6.dispose();
		g7.dispose();
	
		Graphics2D g4 = (Graphics2D) bs.getDrawGraphics();
		
		if(facing == true){
			g4.translate(x + xjoint1, y - yjoint1);
			g4.rotate(Math.toRadians(-rot));
			g4.translate(-x - xjoint1, -y + yjoint1);
			g4.drawImage(img3, x + xjoint1 - xjoint2, y - yjoint1 + yjoint2, null);
		
		}else{
			xjoint1 += character.lefttorso.getWidth();
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

	g3.drawImage(img2, x, y - img2.getHeight(), null);

	g3.dispose();
	
	Graphics2D g5 = (Graphics2D) bs.getDrawGraphics();
	
	if(facing == true){
		g5.translate(x + xjoint3, y - yjoint3);
		g5.rotate(Math.toRadians(rot));
		g5.translate(-x - xjoint3, -y + yjoint3);
		g5.drawImage(img3, x + xjoint3 - xjoint4, y - yjoint3 + yjoint4, null);
	
	}else{
		xjoint3 -= character.lefttorso.getWidth() - xjoint4;
		g5.translate(x + xjoint3, y - yjoint3);
		g5.rotate(Math.toRadians(rot));
		g5.translate(-x - xjoint3, -y + yjoint3);
		g5.drawImage(img3, x + xjoint3 -2 * xjoint4, y - yjoint3 + yjoint4, null);
	}
	g5.dispose();
	
}

	

}
