package main;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import main.GameEntities.PlayerCar;


public class ImagesLoader {

    private URL url;
    private Image img[];
    
    public ImagesLoader() {
        
            img = new Image[6];
        
        try {
            url = getClass().getResource("/Images/Enemy1.png");
            img[0] = ImageIO.read(url);
            url = getClass().getResource("/Images/Enemy2.png");
            img[1] = ImageIO.read(url);
            url = getClass().getResource("/Images/Enemy3.png");
            img[2] = ImageIO.read(url);
            url = getClass().getResource("/Images/Enemy11.png");
            img[3] = ImageIO.read(url);
            url = getClass().getResource("/Images/Enemy22.png");
            img[4] = ImageIO.read(url);
            url = getClass().getResource("/Images/Enemy33.png");
            img[5] = ImageIO.read(url);
        } catch (IOException ex) {
            Logger.getLogger(PlayerCar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Image getImage(int i){
        if(i<0||i>=img.length){
            System.out.println("main.ImagesLoader.getImage()");
            return null;
        }
        return img[i];
    }
    
    
    
}
