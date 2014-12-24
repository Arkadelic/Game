package com.ready.rain.entity.mib;

import com.ready.rain.Game;
import com.ready.rain.entity.Entity;
import com.ready.rain.graphics.Sprite;
import com.ready.rain.level.Level;
import com.ready.rain.level.ReadLevel;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected int comp = 0;
	protected boolean moving = false;
	protected boolean walking = false;
	
	public void move(int xa, int ya){  //the mob tracking method
		// -1, 0 , 1 are the values here that xa or ya can equal to
		if(xa!=0 && ya!= 0){
			move(xa,0);
			move(0,ya);
			return;
		}
		if (xa > 0) comp = 1;
		if (xa < 0) comp = 3;
		if (ya > 0) comp = 2;
		if (ya < 0) comp = 0;
		
		if (!collision(xa, ya)) {
		x += xa;
		y += ya;
		}
	}
	
	public void update(){
	
	}
	private boolean collision(int xa, int ya){
		boolean solid = false;
		int Gw = Game.width/2;
		int Gh = Game.height/2;
		for(int i = 0; i < 4; i++){
			int xcorn = (((x + xa)+ Gw) + i % 2 * 14 - 8)/16;
			int ycorn = (((y + ya)+ Gh) + i / 2 * 10 + 11)/16;
			if (level.getTile(xcorn, ycorn).solid()) solid = true;
		}
		System.out.println(((x + xa)/16 +","+ (y + ya)/16));
		return solid;
	}
	public void render(){
		
	}
	
	
	
	
}

