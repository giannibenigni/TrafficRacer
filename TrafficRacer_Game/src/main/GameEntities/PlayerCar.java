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
    
    private final Random r = new Random();
    private final Handler handler;
    private final Game game;
    
    private URL url;
    private Image img;
    
    public PlayerCar(int x, int y, ID id, Handler handler,Game game) {
        super(x, y, id);
        this.handler = handler;
        this.game = game;
        
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
        
        game.addPoint(((x > (Game.WIDTH/2)) ? 1:2) + game.getVelB());
        
        collision();
    }
    
    @Override
    public void render(Graphics g) {        
        g.drawImage(img, game.r.rx(x), game.r.ry(y), game.r.rx(64), game.r.ry(64), null);
        g.drawRect(game.r.rx(x+10), game.r.ry(y), game.r.rx(45), game.r.ry(64));
        
    }
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x+10, y, 45, 64);
    }
    
    private void collision() {
        
        for (GameObject gameObject : handler.object) {
              if (gameObject.getId() == ID.EnemyCar || gameObject.getId() == ID.EnemyTruck || gameObject.getId() == ID.Obstacle) {
                  
                  Rectangle pC = getBounds();
                  Rectangle eC = gameObject.getBounds();
                  
                if (pC.intersects(eC)) {
                    System.out.println("Collisione!");
                    velY = gameObject.getVelY();
                    
                    game.hud.setGameover();
                    
                    
                    //game.running = false;
                    
                   // game.stop();
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
