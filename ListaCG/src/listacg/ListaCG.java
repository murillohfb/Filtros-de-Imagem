/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listacg;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author CC09512613980
 */
public class ListaCG {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {


        String path = "C:\\Users\\muril\\Desktop\\CG";
        //Exercicio 6
        EscalaDeCinza exercicio6 = new EscalaDeCinza();
        exercicio6.img_toCinza(path, "kick", "jpg");
        
        //Exercicio 7
        YCbCr exercicio7_1 = new YCbCr();
       exercicio7_1.img_toYCbCr(path, "kick", "jpg", true);
        exercicio7_1.img_toYCbCr(path, "kick", "jpg", false);
        HSI exercicio7_2 = new HSI();
        exercicio7_2.img_toYCbCr(path, "kick", "jpg", true);
        exercicio7_2.img_toYCbCr(path, "kick", "jpg", false);    
        
        //Exercicio 8
        Limiarizacao exercicio8 = new Limiarizacao();
        exercicio8.img_limiarColor(path, "kick", "jpg");
        
        //Exercicio 10
        Media exercicio10 = new Media();
        exercicio10.img_media(path, "kick", "jpg", 3);
        exercicio10.img_media(path, "kick", "jpg", 5);
        exercicio10.img_media(path, "kick", "jpg", 7);
        exercicio10.img_media(path, "kick", "jpg", 11);
        
        //Exercicio 11
        Mascara3x3 exercicio11 = new Mascara3x3();
        exercicio11.img_mask(path, "kick", "jpg", true, "0,1,0," + "1,1,1," + "0,1,0", 5);
        exercicio11.img_mask(path, "kick", "jpg", true, "1,1,0," + "1,1,1," + "1,1,1", 9);
        exercicio11.img_mask(path, "kick", "jpg", true, "1,1,1," + "1,2,1," + "1,1,1", 10);
        exercicio11.img_mask(path, "kick", "jpg", true, "1,2,1," + "2,4,2," + "1,2,1", 12);
        exercicio11.img_mask(path, "kick", "jpg", true, "0,1,0," + "1,-4,1," + "0,1,0", 3);
       
        
    }
    
}
