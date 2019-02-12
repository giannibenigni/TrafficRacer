package main.GameEntities;

import java.awt.Graphics;
import java.awt.Rectangle;
import main.Game;
import main.GameObject;
import main.Handler;
import main.ID;

public class EnemyCar extends GameObject{

    private final Handler handler;
    private final Game game;

    private int index = 0 ;
    
    public EnemyCar(int x, int y, ID id, Handler handler, Game game){
        super(x, y, id); 
        this.handler = handler;
        this.game = game;
    }

    @Override
    public void tick() {
        y += velY + ((velY > 0) ? game.getVelBase():-game.getVelBase());
        
        if(y >  Game.HEIGHT+20){
            handler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(game.imageLoader.getCarImage(index),game.r.rx(x), game.r.ry(y), game.r.rx(64), game.r.ry(64), null);
        g.drawRect(game.r.rx(x+10), game.r.ry(y), game.r.rx(45), game.r.ry(64));
    }

    @Override
    public Rectangle getHitbox() {
         return new Rectangle(x+10,y,45,64); 
    }
    
    public void setImage(int i){
        index = i;
    }
    
    
    
    
    
}
