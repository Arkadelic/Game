package com.ready.rain.level;
import com.ready.rain.Game;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.ready.rain.entity.mib.SpiderG;
import com.ready.rain.level.tile.Tile;

public class ReadLevel extends Level{

	public static int biome_grass = 0xFF0000FF;
	public static int biome_ocean = 0x00FFFFFF;
	public static int biome_desert = 0xFF0000FF;
	
	private int[] wrldpx;
	
	
	public ReadLevel (String path) {
		super (path);	
	}

		   
	protected void loadLevel (String path) {  
		try{
			BufferedImage image = ImageIO.read(ReadLevel.class.getResource(path));
		int w = width = image.getWidth();
		int h = height = image.getHeight();	
		tiles = new Tile [w * h];
		wrldpx = new int [w * h];		
		image.getRGB(0, 0, w, h, wrldpx, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Problem in Reading / loading image file"); 
		}
		add (new SpiderG(1180, 1260));
	}
	
	protected void generateLevel() {
		for (int p = 0; p < wrldpx.length; p++){
			int tilenum = (int) (Math.random()*256);
			if (wrldpx[p] == 0xFF0000FF) {
				if(tilenum < 10){
				tiles[p] = Tile.grass4;
		}		else if (tilenum >= 10 && tilenum < 20){
				tiles[p] = Tile.grass5;
		}		else if (tilenum >= 20 && tilenum < 25){
				tiles[p] = Tile.grass2;
		}		else if (tilenum >= 25 && tilenum < 30){
				tiles[p] = Tile.grass3;
		}		else{
				tiles[p] = Tile.grass;
		}
			}
			else	if (wrldpx[p] == 0xFFFF00FF) {
				tiles[p] = Tile.grass3;
		}		
			else if (wrldpx[p] == 0xFF00FFFF) {
				if(tilenum < 10){
				tiles[p] = Tile.grass4;
		}		else if (tilenum >= 10 && tilenum < 20){
				tiles[p] = Tile.grass5;
		}		else if (tilenum >= 20 && tilenum < 25){
				tiles[p] = Tile.grass2;
		}		else if (tilenum >= 25 && tilenum < 30){
				tiles[p] = Tile.grass3;
		}		else{
				tiles[p] = Tile.grass;
		}
		}
			else if (wrldpx[p] == biome_ocean) {
				tiles[p] = Tile.water;
		}
			else if (wrldpx[p] == 0xFFFFFFFF) {
				tiles[p] = Tile.water;
		}
			else if (wrldpx[p] == 0xFF000000) {
				tiles[p] = Tile.water;	
		}	else if (wrldpx[p] == 0x00FF0000) {
				if(tilenum < 10){
				tiles[p] = Tile.grass4;
		}		else if (tilenum >= 10 && tilenum < 20){
				tiles[p] = Tile.grass5;
		}		else if (tilenum >= 20 && tilenum < 25){
				tiles[p] = Tile.grass2;
		}		else if (tilenum >= 25 && tilenum < 30){
				tiles[p] = Tile.grass3;
		}		else{
				tiles[p] = Tile.grass;
		}
		}
			else if (wrldpx[p] == 0x0000FF00) {
				if(tilenum < 10){
				tiles[p] = Tile.grass4;
		}		else if (tilenum >= 10 && tilenum < 20){
				tiles[p] = Tile.grass5;
		}		else if (tilenum >= 20 && tilenum < 25){
				tiles[p] = Tile.grass2;
		}		else if (tilenum >= 25 && tilenum < 30){
				tiles[p] = Tile.grass3;
		}		else{
				tiles[p] = Tile.grass;
		}
		}
			else	if (wrldpx[p] == 0xFFFF0000) {
				if(tilenum < 10){
				tiles[p] = Tile.grass4;
		}		else if (tilenum >= 10 && tilenum < 20){
				tiles[p] = Tile.grass5;
		}		else if (tilenum >= 20 && tilenum < 25){
				tiles[p] = Tile.grass2;
		}		else if (tilenum >= 25 && tilenum < 30){
				tiles[p] = Tile.grass3;
		}		else{
				tiles[p] = Tile.grass;
		}
		}		
			else if (wrldpx[p] == 0xFF00FF00) {
				if(tilenum < 5){
					tiles[p] = Tile.desert2;
					}		else if (tilenum >= 5 && tilenum < 10){
					tiles[p] = Tile.desert3;
					}		else if (tilenum >= 10 && tilenum < 15){
					tiles[p] = Tile.desert4;
					}		else{
					tiles[p] = Tile.desert;
					}
		}
			else if (wrldpx[p] == 0x00FFFF00) {
				tiles[p] = Tile.grass3;
		}
			else if (wrldpx[p] == 0xFFFFFF00) {
				if(tilenum < 10){
				tiles[p] = Tile.grass4;
		}		else if (tilenum >= 10 && tilenum < 20){
				tiles[p] = Tile.grass5;
		}		else if (tilenum >= 20 && tilenum < 25){
				tiles[p] = Tile.grass2;
		}		else if (tilenum >= 25 && tilenum < 30){
				tiles[p] = Tile.grass3;
		}		else{
				tiles[p] = Tile.grass;
		}
		}
			else if (wrldpx[p] == 0x00000000) {
				tiles[p] = Tile.water;

		} 
		else tiles[p] = Tile.voidTile;
		}
	}
}
		

