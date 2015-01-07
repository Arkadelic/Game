package com.ready.rain.entity;
import com.ready.rain.Game;
import com.ready.rain.entity.Entity;
import com.ready.rain.graphics.Sprite;
import com.ready.rain.entity.mib.PP;

public abstract class Projectile extends Entity{
	
	final protected int xO, yO;
	protected double angle;
	protected Sprite sprite;
	protected double ix,iy;
	protected double speed, RPM, dmg, range;
	
	public Projectile(int x, int y, double comp){
		xO = x;
		yO = y;
		angle = comp;
		this.x = x + Game.getScreenWidth()/2;
		this.y = y+ Game.getScreenHeight()/2 - 16;
	}
	
	protected void move() {
	}


	}
