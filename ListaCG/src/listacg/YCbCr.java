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
public class YCbCr {
    
    void img_toYCbCr(String path, String name, String extension, boolean junto) throws IOException
    {
        File f = new File(path + "\\" + name + "." + extension);
        BufferedImage img = ImageIO.read(f);
        

            BufferedImage YImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
            BufferedImage CbImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
            BufferedImage CrImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());    

            BufferedImage YCbCrImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());  

        
        
        for(int x = 0; x < img.getWidth(); x++){
            for(int y = 0; y < img.getHeight(); y++){
                Color c = new Color(img.getRGB(x, y));
                double r = c.getRed()/255f;
                double g = c.getGreen()/255f;
                double b = c.getBlue()/255f;
                
                double Y, cb, cr;
                
                Y = ((0.2568 * r) + (0.5041 * g) + (0.0979 * b)) + 16;
                cb = ((-0.1482 * r) + (-0.2910 * g) + (0.4392 * b)) + 128;
                cr = ((0.4392 * r) + (-0.3678 * g) + (-0.0714 * b)) + 128;
                
                //Normalizado
                double nY = ((Y-16)/218)*255;
                double ncb = ((cb-16)/224)*255;
                double ncr = ((cr-16)/224)*255;

                int newColorY = (int)nY;
                int newColorcb = (int)ncb;
                int newColorcr = (int)ncr;
                
                if(newColorY > 255){
                    newColorY = 255;
                }else if(newColorY < 0){
                    newColorY = 0;
                }
                if(newColorcb > 255){
                    newColorcb = 255;
                }else if(newColorcb < 0){
                    newColorcb = 0;
                }
                if(newColorcr > 255){
                    newColorcr = 255;
                }else if(newColorcr < 0){
                    newColorcr = 0;
                }
                
                if (!junto)
                {
                Color novaCory = new Color(newColorY,newColorY,newColorY);
                YImage.setRGB(x, y, novaCory.getRGB());
                
                Color novaCorcb = new Color(newColorcb,newColorcb,newColorcb);
                CbImage.setRGB(x, y, novaCorcb.getRGB());
                
                Color novaCorcr = new Color(newColorcr,newColorcr,newColorcr);
                CrImage.setRGB(x, y, novaCorcr.getRGB());                    
                }
                else
                {
                Color novaCorYCbCr = new Color(newColorY,newColorcb,newColorcr);
                YCbCrImage.setRGB(x, y, novaCorYCbCr.getRGB());                      
                }

                //System.out.println("Pos("+ x + "," + y + ") R = " + r + " G = " + g + " B = " + b + " RGB Int = " + rgb + " RGB Hex = " + Integer.toHexString(rgb));
            }
        }
        if(!junto)
        {
        File outputFile = new File(path + "\\" + name + "_Y." + extension);
        ImageIO.write(YImage, extension, outputFile);
        
        File output2File = new File(path + "\\" + name + "_Cb." + extension);
        ImageIO.write(CbImage, extension, output2File);
        
        File output3File = new File(path + "\\" + name + "_Cr." + extension);
        ImageIO.write(CrImage, extension, output3File);            
        }
        else
        {
        File output4File = new File(path + "\\" + name + "_YCbCr." + extension);
        ImageIO.write(YCbCrImage, extension, output4File);               
        }

    
}
    
}
