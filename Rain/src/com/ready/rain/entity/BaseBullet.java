package com.ready.rain.entity;

import java.util.ArrayList;
import java.util.List;

import com.ready.rain.Game;
import com.ready.rain.graphics.Screen;
import com.ready.rain.graphics.Sprite;
import com.ready.rain.entity.Entity;
import com.ready.rain.entity.mib.Bullet;

public class BaseBullet extends Projectile{

	public BaseBullet (int x, int y, double comp) {
	super(x, y, comp);
	range = 100;
	sprite = Sprite.Bullet;
	speed = 5;
	dmg = 50;
	RPM = 20;
	ix = speed * Math.cos(angle);
	iy = speed * Math.sin(angle);
	}
	public void update(){
		move();
	}
	protected void move() {
		x += ix;
		y += iy;
		
		
	}
	public void render(Screen screen){
		screen.renderTile(x, y, sprite);
	}
}
