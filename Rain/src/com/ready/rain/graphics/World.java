package com.ready.rain.graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.ready.rain.ImageWriter;
public final class World {

	private String path;
	 public final int WSIZE;
	 public int[] biomes;
	 public static float rand = ImageWriter.rand;
	 public static World worldtiles = new World("/src/" + rand +"world.png", 3000);
	 
	 private World (String path, int wsize){
		 this.path = path;
		 WSIZE = wsize;
		 biomes = new int[WSIZE *WSIZE];
		 load();		 
	 }
	 private void load() {
		  try {
			BufferedImage image = ImageIO.read(World.class.getResource(path));
		int w = image.getWidth();
		int h = image.getHeight();
		image.getRGB(0, 0, w, h, biomes, 0, w);
		  } catch (IOException e) {
			e.printStackTrace();
		}
	 }
	 
	 

}
