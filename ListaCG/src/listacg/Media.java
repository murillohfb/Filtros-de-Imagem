/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listacg;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Media {
     void img_media(String path, String name, String extension, int dimension) throws IOException
    {
        File f = new File(path + "\\" + name + "." + extension);
        BufferedImage img = ImageIO.read(f);
 
        BufferedImage mediaImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());  

        //D = 3  R=1 x-1
        //D = 5  R=2 x-2
        //D = 7  R=3 x-(d - (d/2+1))
        //D = 9  R=4 x-4
        //D = 11
        
        for(int x = 0; x < img.getWidth(); x++){
            for(int y = 0; y < img.getHeight(); y++){
                Color c = new Color(img.getRGB(x, y));
                double r = c.getRed()/255f;
                double g = c.getGreen()/255f;
                double b = c.getBlue()/255f;
                
                int raio = (dimension - ((dimension/2)+1));
                int somaR = 0;
                int somaG = 0;
                int somaB = 0;
                int cont = 0;
                for(int j = x-raio; j <= x+raio ; j++)
                {
                    
                   for(int k = y-raio; k <=y+raio; k++)
                   {
                       if(k>=0 && j>=0 && k<img.getHeight() && j<img.getWidth())
                       {
                            Color cor = new Color(img.getRGB(j, k));
                            somaR += cor.getRed();
                            somaG += cor.getGreen();
                            somaB += cor.getBlue();
                            cont++;
                       }
                       


                   }
                }
                
                somaR = somaR/cont;
                somaG = somaG/cont;
                somaB = somaB/cont;
               
                
                Color RGB = new Color(somaR,somaG,somaB);
                mediaImage.setRGB(x, y, RGB.getRGB());


                //System.out.println("Pos("+ x + "," + y + ") R = " + r + " G = " + g + " B = " + b + " RGB Int = " + rgb + " RGB Hex = " + Integer.toHexString(rgb));
            }
        }

        File outputFile = new File(path + "\\" + name + "_media"+ dimension+ "X"+ dimension+ "." + extension);
        ImageIO.write(mediaImage, extension, outputFile);          


    
}
}
