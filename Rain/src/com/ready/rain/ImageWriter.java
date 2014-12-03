package com.ready.rain;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.ready.rain.level.Level;
import com.ready.rain.level.ReadLevel;
import com.ready.rain.Game;

import java.util.Random;

public class ImageWriter {
	protected static final Random random = new Random();
	protected static final Random random1 = new Random();
	public static float rand;
	private static float i = 0.5f;
	private static float rand1= i;



    public static void greyWriteImage(double [][] data,double [][] data2,double [][] data3){
        //this takes and array of doubles between 0 and 1 and generates a grey scale image from them
    	
    	System.out.println("creating world");

        BufferedImage image = new BufferedImage(data.length,data[0].length, BufferedImage.TYPE_INT_RGB);
        rand = random.nextInt(64);
       
        for (int y = 0; y < data[0].length; y++)
        {
        	
          for (int x = 0; x < data.length; x++)
        	  
          {
// add more variety through smaller ranges
            if (data[x][y]>= 0.5){
                data[x][y]=1;
            }
            if (data[x][y] < 0.5){
                data[x][y]=0;
            }
        /*    if (data[x][y] >= 0.3 && data[x][y] < 0.5){
                data[x][y]=0.3;
            }
           if (data[x][y] >= 0.1 && data[x][y] < 0.3){
                data[x][y]=0;
            }*/
            if (data2[x][y]>= 0.5){
                data2[x][y]=1;
            }
            if (data2[x][y] < 0.5){
                data2[x][y]=0;
            }
           /* if (data2[x][y] >= 0.3 && data2[x][y] < 0.5){
                data2[x][y]=0.3;
            }
            if (data2[x][y] >= 0.1 && data2[x][y] < 0.3){
                data2[x][y]=0;
            }*/
            if (data3[x][y]>= 0.5){
                data3[x][y]=1;
            }
            if (data3[x][y] < 0.5 ){
                data3[x][y]=0;
            }
           /* if (data3[x][y] >= 0.3 && data3[x][y] < 0.5){
                data3[x][y]=0.3;
            }
            if (data3[x][y] >= 0.1 && data3[x][y] < 0.3){
                data3[x][y]=0;
            }*/

          



            
            Color col=new Color((float)data[x][y],(float)data2[x][y],(float)data3[x][y]); 
            image.setRGB(x, y, col.getRGB());
          }
        }
      

      

        try {
            // retrieve image
            File outputfile = new File("res/textures/world" + (int)rand + ".png" );
            outputfile.createNewFile();
            System.out.println("done");
            ImageIO.write(image, "png", outputfile);


        } catch (IOException e) {
            //o no! Blank catches are bad
            throw new RuntimeException("I didn't handle this very well");
        }
        
  
        Game.level = new ReadLevel("/textures/world" + (int)rand + ".png" );
			Game.loaded = true;
  
    }

	public static float getRand() {
		return rand;
	}

	public static void setRand(float rand) {
		ImageWriter.rand = rand;
	}



}