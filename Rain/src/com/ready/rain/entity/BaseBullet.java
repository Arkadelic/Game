package com.ready.rain.entity;

import java.util.ArrayList;
import java.util.List;

import com.ready.rain.Game;
import com.ready.rain.graphics.Screen;
import com.ready.rain.graphics.Sprite;
import com.ready.rain.entity.Entity;
import com.ready.rain.entity.mib.Bullet;

public class BaseBullet extends Projectile{
	public static List<Bullet>bullets = new ArrayList <Bullet>();
	public BaseBullet (int x, int y, double comp) {
	super(x, y, comp);
	range = 100;
	sprite = Sprite.grass;
	speed = 5;
	dmg = 50;
	RPM = 20;
	ix = speed * Math.cos(angle);
	iy = speed * Math.sin(angle);
	}
	public void update(){
		if(Game.click == 1){
			System.out.println("YES");
			if(Game.Player.gun.type == "pistol"){
				Game.c = Game.animation.target + 90;
				if(Game.Player.gun.cd < 0){
				Bullet bullet = new Bullet(Game.c,Game.Player.gun,Game.getScreenWidth()/2,Game.getScreenHeight()/2);
				bullets.add(bullet);
				Game.Player.gun.cd = 100;
				}
				}
			
				
		}

		Game.Player.gun.cd --;
	}
	protected void move() {
		x += ix;
		y += iy;
		
		
	}
	public void render(Screen screen){
		screen.renderTile(x, y, sprite);
	}
}
