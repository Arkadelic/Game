package com.ready.rain.entity.mib;

import com.ready.rain.graphics.Screen;
import com.ready.rain.graphics.Sprite;

public class SpiderG extends Mob{

	public SpiderG(int x,int y){
		this.x = x * 16;
		this.y = y * 16;
		sprite = Sprite.water;
	}
	public void update() {
System.out.println("X :" + this.x + " Y : " + this.y);
		
	}


	public void render(Screen screen) {
		screen.renderTile(x, y, sprite);
	}

}
