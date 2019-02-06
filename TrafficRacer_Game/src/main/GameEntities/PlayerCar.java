package main.GameEntities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import main.Game;
import main.GameObject;
import main.Handler;
import main.ID;

public class PlayerCar extends GameObject {
    
    Random r = new Random();
    Handler handler;
    
    URL url;
    Image img;
    
    public PlayerCar(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        
        url = getClass().getResource("/Images/playerCar.png");
        try {
            img = ImageIO.read(url);
        } catch (IOException ex) {
            Logger.getLogger(PlayerCar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void tick() {
        x += velX;
        y += velY;
        
        x = Game.clamp(x, 100, Game.WIDTH - 164);
        //y = Game.clamp(y, 0, Game.HEIGHT - 60);
        
        collision();
    }
    
    @Override
    public void render(Graphics g) {        
        g.drawImage(img, x, y, 64, 64, null);
        g.drawRect(x, y, 64, 64);
        
    }
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 64, 64);
    }
    
    private void collision() {
        
        for (GameObject gameObject : handler.object) {
              if (gameObject.getId() == ID.EnemyCar) {
                  
                  Rectangle pC = getBounds();
                  Rectangle eC = gameObject.getBounds();
                  
                if (pC.intersects(eC)) {
                    
                }
            }
        }
        
    }

    @Override
    public void setVelY(int velY) {
         for (GameObject gameObject : handler.object) {
              if (gameObject.getId() == ID.BackGround) {
                  gameObject.setVelY(-velY);
              }
        }
        
        super.setVelY(0);
    }
    
    
    
    
}
