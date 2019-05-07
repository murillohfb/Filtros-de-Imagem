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

public class Limiarizacao {

    public void img_limiarColor(String path, String name, String extension) throws IOException 
    {
        File f = new File(path + "\\" + name + "." + extension);
        BufferedImage img = ImageIO.read(f);

        BufferedImage limiarImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        int redValue = 0;
        int greenValue = 0;
        int blueValue = 0;
        int type = 0;
        
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                Color c = new Color(img.getRGB(x, y));
                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();
                
                redValue += r;
                greenValue += g;
                blueValue += b;
                
                
                
            }
        }
        if (redValue > greenValue && redValue > blueValue)
        {
            type = 0;
        }
        else if(greenValue > redValue && greenValue > blueValue)
        {
            type = 1;
        }
        else
        {
            type = 2;
        }
        
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                Color c = new Color(img.getRGB(x, y));
                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();
                
                if (type == 0)
                {
                    if(r>g && r>b)
                    {
                        Color novaCor = new Color(255,255,255);
                        limiarImage.setRGB(x, y, novaCor.getRGB());
                    }
                    else
                    {
                        Color novaCor = new Color(0,0,0);
                        limiarImage.setRGB(x, y, novaCor.getRGB());
                    }

                }
                else if (type == 1)
                {
                    if(g>r && g>b)
                    {
                        Color novaCor = new Color(255,255,255);
                        limiarImage.setRGB(x, y, novaCor.getRGB());
                    }
                    else
                    {
                        Color novaCor = new Color(0,0,0);
                        limiarImage.setRGB(x, y, novaCor.getRGB());
                    }

                }
                if (type == 2)
                {
                    if(b>r && b>g)
                    {
                        Color novaCor = new Color(255,255,255);
                        limiarImage.setRGB(x, y, novaCor.getRGB());
                    }
                    else
                    {
                        Color novaCor = new Color(0,0,0);
                        limiarImage.setRGB(x, y, novaCor.getRGB());
                    }

                }                
            }
        }
        File outputFile = new File(path + "\\" + name + "_limiar." + extension);
        ImageIO.write(limiarImage, extension, outputFile);   
    }
}
