package com.ready.rain;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;

public class ImageWriter {
	protected static final Random random = new Random();
	protected static final Random random1 = new Random();
	private static float rand;
	private static float rand1;
	private static float prevrand = 0.1f;

    public static void greyWriteImage(double [][] data){
        //this takes and array of doubles between 0 and 1 and generates a grey scale image from them

        BufferedImage image = new BufferedImage(data.length,data[0].length, BufferedImage.TYPE_INT_RGB);
        rand = random.nextInt(64);
       
        

        for (int y = 0; y < data[0].length; y++)
        {
        	
          for (int x = 0; x < data.length; x++)
          {
        	  rand1= 0.5f;
        	  if(Math.random() >= 0.5)rand1 -= Math.random()*prevrand/10;
        	  if(Math.random() < 0.5)rand1 += Math.random()*prevrand/10;
              prevrand = rand1;
        	  data[x][y] = rand1;
           if (data[x][y]>1){
                data[x][y]=1;
            }
            if (data[x][y]<0){
                data[x][y]=0;
            }
            if (data[x][y]<0 && data[x][y]>0.1){
                data[x][y]=0.05;
            }
            if (data[x][y]<0.1 && data[x][y]>0.2){
                data[x][y]=0.15;
            }
            if (data[x][y]<0.2 && data[x][y]>0.3){
                data[x][y]=0.25;
            }
            if (data[x][y]<0.3 && data[x][y]>0.4){
                data[x][y]=0.35;
            }
            if (data[x][y]<0.4 && data[x][y]>0.5){
                data[x][y]=0.45;
            }
            if (data[x][y]<0.5 && data[x][y]>0.6){
                data[x][y]=0.55;
            }
            if (data[x][y]<0.6 && data[x][y]>0.7){
                data[x][y]=0.65;
            }
            if (data[x][y]<0.7 && data[x][y]>0.8){
                data[x][y]=0.75;
            }
            if (data[x][y]<0.8 && data[x][y]>0.9){
                data[x][y]=0.85;
            }
            if (data[x][y]<0.9 && data[x][y]>1){
                data[x][y]=0.95;
            }

            
            
            
            
              Color col=new Color((float)data[x][y],(float)data[x][y],(float)data[x][y]); 
            image.setRGB(x, y, col.getRGB());
          }
          
        }

        try {
            // retrieve image
            File outputfile = new File("world" + rand + ".png" );
            outputfile.createNewFile();

            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            //o no! Blank catches are bad
            throw new RuntimeException("I didn't handle this very well");
        }
    }

	public static float getRand() {
		return rand;
	}

	public static void setRand(float rand) {
		ImageWriter.rand = rand;
	}



}