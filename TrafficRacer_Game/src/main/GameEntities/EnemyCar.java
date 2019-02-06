package main.GameEntities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
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

public class EnemyCar extends GameObject{

    Handler handler;
    Game game;
    
    //URL url;
    //Image img[];
    private int index = 0 ;
    
    Random r;
    
    public EnemyCar(int x, int y, ID id, Handler handler, Game game){
        super(x, y, id); 
        this.handler = handler;
        this.game = game;
    }

    @Override
    public void tick() {
        y += velY + ((velY > 0) ? game.getVelB():-game.getVelB());
        
        if(y >  Game.HEIGHT+20){
            handler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(game.loader.getImage(index),game.r.rx(x), game.r.ry(y), game.r.rx(64), game.r.ry(64), null);
    }

    @Override
    public Rectangle getBounds() {
         return new Rectangle(x,y,64,64); 
    }
    
    public void setImage(int i){
        index = i;
    }
    
    
    
    
    
}
