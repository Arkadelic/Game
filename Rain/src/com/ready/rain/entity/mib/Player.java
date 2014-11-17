package com.ready.rain.entity.mib;

import java.awt.image.BufferedImage;


public class Player extends Mob {
	
	public int x;
	public int y;
	public int xjoint1;
	public int yjoint1;
	public int xjoint2;
	public int yjoint2;
	public int xjoint3;
	public int yjoint3;
	public int xjoint4;
	public int yjoint4;
	public BufferedImage righttorso;
	public BufferedImage lefttorso;
	public BufferedImage rightarm;
	public BufferedImage leftarm;
	
	public Player (int x, int y,BufferedImage righttorso,BufferedImage lefttorso,BufferedImage rightarm,BufferedImage leftarm,int xjoint1,int yjoint1,int xjoint2,int yjoint2,int xjoint3,int yjoint3,int xjoint4,int yjoint4) {
		 
		this.righttorso = righttorso;
		this.lefttorso = lefttorso;
		this.rightarm = rightarm;
		this.leftarm = leftarm;
		this.xjoint1 = xjoint1;
		this.yjoint1 = yjoint1 + righttorso.getHeight()/2;
		System.out.println(yjoint1 + righttorso.getHeight()/2);
		this.xjoint2 = xjoint2;
		this.yjoint2 = yjoint2;
		this.xjoint3 = xjoint3;
		this.yjoint3 = yjoint3 + righttorso.getHeight()/2;
		this.xjoint4 = xjoint4;
		this.yjoint4 = yjoint4;
		this.x = x - righttorso.getWidth()/2;
		this.y = y + righttorso.getHeight()/2;
		 
		}
}
