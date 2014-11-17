package com.ready.rain.entity.mib;

import com.ready.rain.entity.Entity;
import com.ready.rain.graphics.Sprite;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected int dir = 0;
	protected boolean moving = false;
	
	public void move(){
	}
	
	public void update(){
		
	}
	
	private boolean collision(){
		return false;
	}
	
	
}

