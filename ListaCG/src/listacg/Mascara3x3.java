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
public class Mascara3x3 {
    
    public void img_mask(String path, String name, String extension, boolean junto, String mascara, int divide) throws IOException
    {
         File f = new File(path + "\\" + name + "." + extension);
        BufferedImage img = ImageIO.read(f);
        
        BufferedImage novaR = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        BufferedImage novaG = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        BufferedImage novaB = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        
        BufferedImage novaRGB = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());

        String[] newMascara = mascara.split(",");
        int mf[][] = new int[3][3];
        mf[0][0] = Integer.parseInt(newMascara[0]);
        mf[0][1] = Integer.parseInt(newMascara[1]);
        mf[0][2] = Integer.parseInt(newMascara[2]);
        
        mf[1][0] = Integer.parseInt(newMascara[3]);
        mf[1][1] = Integer.parseInt(newMascara[4]);
        mf[1][2] = Integer.parseInt(newMascara[5]);
        
        mf[2][0] = Integer.parseInt(newMascara[6]);
        mf[2][1] = Integer.parseInt(newMascara[7]);
        mf[2][2] = Integer.parseInt(newMascara[8]);
        
        
        for(int x = 0; x < img.getWidth(); x++){
            for(int y = 0; y < img.getHeight(); y++){
                Color c = new Color(img.getRGB(x, y));
                          
               
                double r = c.getRed()/255f;
                double g = c.getGreen()/255f;
                double b = c.getBlue()/255f;
                
                int mediaR;
                int mediaG;
                int mediaB;
                
                if(x== 0 && y ==0) //ponto esquerdo cima
                {
                    Color cDOWN = new Color(img.getRGB(x, y+1));
                    Color cRIGHT = new Color(img.getRGB(x+1, y));
                    Color cDR = new Color(img.getRGB(x+1, y+1));
                    
                    mediaR = (c.getRed()*mf[1][1] + c.getRed()*mf[0][0] + c.getRed()*mf[1][0] + c.getRed()*mf[0][1] + 
                              cRIGHT.getRed()*mf[1][2] + cRIGHT.getRed()*mf[0][2] + 
                              cDOWN.getRed()*mf[2][0] + cDOWN.getRed()*mf[2][1] + 
                              cDR.getRed()*mf[2][2])/divide;
                    
                    mediaG = (c.getGreen()*mf[1][1] + c.getGreen()*mf[0][0] + c.getGreen()*mf[1][0] + c.getGreen()*mf[0][1] + 
                              cRIGHT.getGreen()*mf[1][2] + cRIGHT.getGreen()*mf[0][2] + 
                              cDOWN.getGreen()*mf[2][0] + cDOWN.getGreen()*mf[2][1] + 
                              cDR.getGreen()*mf[2][2])/divide;

                    mediaB = (c.getBlue()*mf[1][1] + c.getBlue()*mf[0][0] + c.getBlue()*mf[1][0] + c.getBlue()*mf[0][1] + 
                              cRIGHT.getBlue()*mf[1][2] + cRIGHT.getBlue()*mf[0][2] + 
                              cDOWN.getBlue()*mf[2][0] + cDOWN.getBlue()*mf[2][1] + 
                              cDR.getBlue()*mf[2][2])/divide;
                }

                else if (x == img.getWidth()-1  && y == img.getHeight() - 1) //Ponto direito baixo
                {
                    Color cUP = new Color(img.getRGB(x, y-1));
                    Color cLEFT = new Color(img.getRGB(x-1, y));
                    Color cUL = new Color(img.getRGB(x-1, y-1));
                    
                    mediaR = (c.getRed()*mf[1][1] + c.getRed()*mf[1][2] + c.getRed()*mf[2][2] + c.getRed()*mf[2][1] +
                              cLEFT.getRed()*mf[2][0] + cLEFT.getRed()*mf[1][0] +
                              cUP.getRed()*mf[0][1] * cUP.getRed()*mf[0][2] +
                              cUL.getRed()*mf[0][0]
                            )/divide;
                    
                    mediaG = (c.getGreen()*mf[1][1] + c.getGreen()*mf[1][2] + c.getGreen()*mf[2][2] + c.getGreen()*mf[2][1] +
                              cLEFT.getGreen()*mf[2][0] + cLEFT.getGreen()*mf[1][0] +
                              cUP.getGreen()*mf[0][1] * cUP.getGreen()*mf[0][2] +
                              cUL.getGreen()*mf[0][0]
                            )/divide;
                    
                    mediaB = (c.getBlue()*mf[1][1] + c.getBlue()*mf[1][2] + c.getBlue()*mf[2][2] + c.getBlue()*mf[2][1] +
                              cLEFT.getBlue()*mf[2][0] + cLEFT.getBlue()*mf[1][0] +
                              cUP.getBlue()*mf[0][1] * cUP.getBlue()*mf[0][2] +
                              cUL.getBlue()*mf[0][0]
                            )/divide;
                    
                    }
                else if(y == 0 && x == img.getWidth() - 1) //Ponto direito cima
                {
                    Color cDOWN = new Color(img.getRGB(x, y+1));
                    Color cLEFT = new Color(img.getRGB(x-1, y));
                    Color cDL = new Color(img.getRGB(x-1, y+1));
                    
                    mediaR = (c.getRed()*mf[1][1] + c.getRed()*mf[1][2] + c.getRed()*mf[0][2] + c.getRed()*mf[0][1] +
                              cLEFT.getRed()*mf[1][0] + cLEFT.getRed()*mf[0][0] +
                              cDOWN.getRed()*mf[2][1] + cDOWN.getRed()*mf[2][2] +
                              cDL.getRed()*mf[2][0]
                            )/divide;
                    
                    mediaG = (c.getGreen()*mf[1][1] + c.getGreen()*mf[1][2] + c.getGreen()*mf[0][2] + c.getGreen()*mf[0][1] +
                              cLEFT.getGreen()*mf[1][0] + cLEFT.getGreen()*mf[0][0] +
                              cDOWN.getGreen()*mf[2][1] + cDOWN.getGreen()*mf[2][2] +
                              cDL.getGreen()*mf[2][0]
                            )/divide;
                    
                    mediaB = (c.getBlue()*mf[1][1] + c.getBlue()*mf[1][2] + c.getBlue()*mf[0][2] + c.getBlue()*mf[0][1] +
                              cLEFT.getBlue()*mf[1][0] + cLEFT.getBlue()*mf[0][0] +
                              cDOWN.getBlue()*mf[2][1] + cDOWN.getBlue()*mf[2][2] +
                              cDL.getBlue()*mf[2][0]
                            )/divide;
                    
                }
                else if(y == img.getHeight() - 1 && x == 0) //Ponto esquerda baixo
                {
                    Color cUP = new Color(img.getRGB(x, y-1));
                    Color cRIGHT = new Color(img.getRGB(x+1, y));  
                    Color cUR = new Color(img.getRGB(x+1, y-1));
                    
                    mediaR = (c.getRed()*mf[1][1] + c.getRed()*mf[2][0] + c.getRed()*mf[2][1] + c.getRed()*mf[1][0] +
                              cRIGHT.getRed()*mf[1][2] + cRIGHT.getRed()*mf[2][2] +
                              cUP.getRed()*mf[0][1] + cUP.getRed()*mf[0][0] +
                              cUR.getRed()*mf[0][2]
                            )/divide;
                    mediaG = (c.getGreen()*mf[1][1] + c.getGreen()*mf[2][0] + c.getGreen()*mf[2][1] + c.getGreen()*mf[1][0] +
                              cRIGHT.getGreen()*mf[1][2] + cRIGHT.getGreen()*mf[2][2] +
                              cUP.getGreen()*mf[0][1] + cUP.getGreen()*mf[0][0] +
                              cUR.getGreen()*mf[0][2]
                            )/divide;
                    
                    mediaB = (c.getBlue()*mf[1][1] + c.getBlue()*mf[2][0] + c.getBlue()*mf[2][1] + c.getBlue()*mf[1][0] +
                              cRIGHT.getBlue()*mf[1][2] + cRIGHT.getBlue()*mf[2][2] +
                              cUP.getBlue()*mf[0][1] + cUP.getBlue()*mf[0][0] +
                              cUR.getBlue()*mf[0][2]
                            )/divide;
                    
                }
                else if(x==0) //Borda esquerda
                {
                    Color cUP = new Color(img.getRGB(x, y-1));
                    Color cUR = new Color(img.getRGB(x+1, y-1));
                    Color cRIGHT = new Color(img.getRGB(x+1, y));   
                    Color cDR = new Color(img.getRGB(x+1, y+1));
                    Color cDOWN = new Color(img.getRGB(x, y+1));
                    
                    mediaR = (c.getRed()*mf[1][1] + c.getRed()*mf[1][0] +
                             cUP.getRed()*mf[0][0] + cUP.getRed()*mf[0][1] +
                             cDOWN.getRed()*mf[2][1] + cDOWN.getRed()*mf[2][0] +
                             cDR.getRed()*mf[2][2] +
                             cUR.getRed()*mf[0][2] +
                             cRIGHT.getRed()*mf[1][2]
                            )/divide;
                
                    mediaG = (c.getGreen()*mf[1][1] + c.getGreen()*mf[1][0] +
                             cUP.getGreen()*mf[0][0] + cUP.getGreen()*mf[0][1] +
                             cDOWN.getGreen()*mf[2][1] + cDOWN.getGreen()*mf[2][0] +
                             cDR.getGreen()*mf[2][2] +
                             cUR.getGreen()*mf[0][2] +
                             cRIGHT.getGreen()*mf[1][2]
                            )/divide;
                
                    mediaB = (c.getBlue()*mf[1][1] + c.getBlue()*mf[1][0] +
                             cUP.getBlue()*mf[0][0] + cUP.getBlue()*mf[0][1] +
                             cDOWN.getBlue()*mf[2][1] + cDOWN.getBlue()*mf[2][0] +
                             cDR.getBlue()*mf[2][2] +
                             cUR.getBlue()*mf[0][2] +
                             cRIGHT.getBlue()*mf[1][2]
                            )/divide;
                }
                else if(y ==0) //Borda superior
                {
                    Color cDOWN = new Color(img.getRGB(x, y+1));
                    Color cDR = new Color(img.getRGB(x+1, y+1));
                    Color cRIGHT = new Color(img.getRGB(x+1, y));
                    Color cDL = new Color(img.getRGB(x-1, y+1));
                    Color cLEFT = new Color(img.getRGB(x-1, y));
                    
                    mediaR = (c.getRed()*mf[1][1] + c.getRed()*mf[0][1] +
                              cLEFT.getRed()*mf[1][0] + cLEFT.getRed()*mf[0][0] +
                              cRIGHT.getRed()*mf[1][2] + cRIGHT.getRed()*mf[0][2] +
                              cDOWN.getRed()*mf[2][1] +
                              cDL.getRed()*mf[2][0] +
                              cDR.getRed()*mf[2][2]                            
                            )/divide;
                
                    mediaG = (c.getGreen()*mf[1][1] + c.getGreen()*mf[0][1] +
                              cLEFT.getGreen()*mf[1][0] + cLEFT.getGreen()*mf[0][0] +
                              cRIGHT.getGreen()*mf[1][2] + cRIGHT.getGreen()*mf[0][2] +
                              cDOWN.getGreen()*mf[2][1] +
                              cDL.getGreen()*mf[2][0] +
                              cDR.getGreen()*mf[2][2]                            
                            )/divide;
                    
                    mediaB = (c.getBlue()*mf[1][1] + c.getBlue()*mf[0][1] +
                              cLEFT.getBlue()*mf[1][0] + cLEFT.getBlue()*mf[0][0] +
                              cRIGHT.getBlue()*mf[1][2] + cRIGHT.getBlue()*mf[0][2] +
                              cDOWN.getBlue()*mf[2][1] +
                              cDL.getBlue()*mf[2][0] +
                              cDR.getBlue()*mf[2][2]                            
                            )/divide;
                    
                    
                }
                else if (x == img.getWidth()-1) //Borda direita
                {
                    Color cLEFT = new Color(img.getRGB(x-1, y));
                    Color cDL = new Color(img.getRGB(x-1, y+1));
                    Color cDOWN = new Color(img.getRGB(x, y+1));
                    Color cUL = new Color(img.getRGB(x-1, y-1));
                    Color cUP = new Color(img.getRGB(x, y-1));
                    
                    mediaR = (c.getRed()*mf[1][1] + c.getRed()*mf[1][2] +
                              cUP.getRed()*mf[0][1] + cUP.getRed()*mf[0][2] +
                              cDOWN.getRed()*mf[2][1] + cDOWN.getRed()*mf[2][2] +
                              cLEFT.getRed()*mf[1][0] +
                              cDL.getRed()*mf[2][0] +
                              cUL.getRed()*mf[0][0] )/divide;
                    mediaG = (c.getGreen()*mf[1][1] + c.getGreen()*mf[1][2] +
                              cUP.getGreen()*mf[0][1] + cUP.getGreen()*mf[0][2] +
                              cDOWN.getGreen()*mf[2][1] + cDOWN.getGreen()*mf[2][2] +
                              cLEFT.getGreen()*mf[1][0] +
                              cDL.getGreen()*mf[2][0] +
                              cUL.getGreen()*mf[0][0] )/divide;
                    mediaB = (c.getBlue()*mf[1][1] + c.getBlue()*mf[1][2] +
                              cUP.getBlue()*mf[0][1] + cUP.getBlue()*mf[0][2] +
                              cDOWN.getBlue()*mf[2][1] + cDOWN.getBlue()*mf[2][2] +
                              cLEFT.getBlue()*mf[1][0] +
                              cDL.getBlue()*mf[2][0] +
                              cUL.getBlue()*mf[0][0] )/divide;
                }
                else if (y == img.getHeight()-1) //Borda inferior
                {
                    Color cUP = new Color(img.getRGB(x, y-1));
                    Color cUL = new Color(img.getRGB(x-1, y-1));
                    Color cLEFT = new Color(img.getRGB(x-1, y));
                    Color cUR = new Color(img.getRGB(x+1, y-1));
                    Color cRIGHT = new Color(img.getRGB(x+1, y));   
                    
                    mediaR = (c.getRed()*mf[1][1] + c.getRed()*mf[2][1] +
                              cUP.getRed()*mf[0][1]  +
                              cLEFT.getRed()*mf[1][0] + cLEFT.getRed()*mf[2][0] +
                              cRIGHT.getRed()*mf[1][2] + cRIGHT.getRed()*mf[2][2] +
                              cUL.getRed()*mf[0][0] +
                              cUR.getRed() * mf[0][2])/divide;
                    mediaG = (c.getGreen()*mf[1][1] + c.getGreen()*mf[2][1] +
                              cUP.getGreen()*mf[0][1]  +
                              cLEFT.getGreen()*mf[1][0] + cLEFT.getGreen()*mf[2][0] +
                              cRIGHT.getGreen()*mf[1][2] + cRIGHT.getGreen()*mf[2][2] +
                              cUL.getGreen()*mf[0][0] +
                              cUR.getGreen() * mf[0][2])/divide;
                    mediaB = (c.getBlue()*mf[1][1] + c.getBlue()*mf[2][1] +
                              cUP.getBlue()*mf[0][1]  +
                              cLEFT.getBlue()*mf[1][0] + cLEFT.getBlue()*mf[2][0] +
                              cRIGHT.getBlue()*mf[1][2] + cRIGHT.getBlue()*mf[2][2] +
                              cUL.getBlue()*mf[0][0] +
                              cUR.getBlue() * mf[0][2])/divide;
                }
                else //Meio da imagem
                {
                    Color cUP = new Color(img.getRGB(x, y-1));
                    Color cUR = new Color(img.getRGB(x+1, y-1));
                    Color cUL = new Color(img.getRGB(x-1, y-1));
                    Color cDL = new Color(img.getRGB(x-1, y+1));
                    Color cDR = new Color(img.getRGB(x+1, y+1));
                    Color cDOWN = new Color(img.getRGB(x, y+1));
                    Color cLEFT = new Color(img.getRGB(x-1, y));
                    Color cRIGHT = new Color(img.getRGB(x+1, y));   
                    
                    mediaR = (c.getRed()*mf[1][1] +
                              cUL.getRed()*mf[0][0] +
                              cUP.getRed()*mf[0][1] +
                              cUR.getRed()*mf[0][2] +
                              cLEFT.getRed()*mf[1][0] +
                              cRIGHT.getRed()*mf[1][2] +
                              cDL.getRed()*mf[2][0] +
                              cDOWN.getRed()*mf[2][1] +
                              cDR.getRed()*mf[2][2]
                            )/divide;
                    
                    
                    mediaG = (c.getGreen()*mf[1][1] +
                              cUL.getGreen()*mf[0][0] +
                              cUP.getGreen()*mf[0][1] +
                              cUR.getGreen()*mf[0][2] +
                              cLEFT.getGreen()*mf[1][0] +
                              cRIGHT.getGreen()*mf[1][2] +
                              cDL.getGreen()*mf[2][0] +
                              cDOWN.getGreen()*mf[2][1] +
                              cDR.getGreen()*mf[2][2]
                            )/divide;
                    
                    mediaB = (c.getBlue()*mf[1][1] +
                              cUL.getBlue()*mf[0][0] +
                              cUP.getBlue()*mf[0][1] +
                              cUR.getBlue()*mf[0][2] +
                              cLEFT.getBlue()*mf[1][0] +
                              cRIGHT.getBlue()*mf[1][2] +
                              cDL.getBlue()*mf[2][0] +
                              cDOWN.getBlue()*mf[2][1] +
                              cDR.getBlue()*mf[2][2]
                            )/divide;
                    
                    
                }
                if (mediaR > 255)
                    mediaR = 255;
                    else if (mediaR < 0)
                    mediaR = 0;
                if (mediaG > 255)
                    mediaG = 255;
                    else if (mediaG < 0)
                    mediaG = 0;
                if (mediaB > 255)
                    mediaB = 255;
                    else if (mediaB < 0)
                    mediaB = 0;
                
                Color corNova = new Color (mediaR,mediaG,mediaB);
                    
                Color novaCorR = new Color(mediaR,mediaR,mediaR);
                novaR.setRGB(x, y, novaCorR.getRGB());
                Color novaCorG = new Color(mediaG,mediaG,mediaG);
                novaG.setRGB(x, y, novaCorG.getRGB());
                Color novaCorB = new Color(mediaB, mediaB, mediaB);
                novaB.setRGB(x, y, novaCorB.getRGB());
                
                Color novaCorRGB = new Color(mediaR, mediaG, mediaB);
                novaRGB.setRGB(x, y, novaCorRGB.getRGB());
                
               
                
                
                
                //double Y = ((double)r*0.2568 + (double)g*0.5041 + (double)b*0.0979) + 16;
                //double Cb = ((double)r*-0.1482 + (double)g*-0.2910 + (double)b*0.4392) + 128;
                //double Cr = ((double)r*0.4392 + (double)g*-0.3678 + (double)b*-0.0714) + 128;
                
                //double nY = ((Y - 16)/218)*255;
                //double nCb = ((Cb - 16)/224)*255;
                //double nCr = ((Cr - 16)/224)*255;
                
                
      
                
                

            }
        }
        if(!junto)
        {
        File outputFile = new File(path + "\\" + name+ "_maskR" + divide+ "." + extension);
        ImageIO.write(novaR, extension, outputFile);
        File outputFile2 = new File(path + "\\" + name + "_maskG" + divide+ "." + extension);
        ImageIO.write(novaG,extension, outputFile2);
        File outputFile3 = new File(path + "\\" + name + "_maskB" + divide+ "." + extension);
        ImageIO.write(novaB, extension, outputFile3);            
        }
        else
        {
        File outputFile4 = new File(path + "\\" + name + "_maskRGB" + divide + "." + extension);
        ImageIO.write(novaRGB, extension, outputFile4);                 
        }

        
        
    }
    
}
    

