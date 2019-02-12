/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.GameEntities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import main.Game;
import main.GameObject;
import main.Handler;
import main.ID;

/**
 *
 * @author Gianni
 */
public class EnemyTruck extends GameObject{

    private final Handler handler;
    private final Game game;
    
    private int index = 0 ;  
    
    public EnemyTruck(int x, int y, ID id ,Handler handler, Game game) {
        super(x, y, id);
        this.handler = handler;
        this.game = game;
    }

    @Override
    public void tick() {
        y += velY + ((velY > 0) ? game.getVelBase() : -game.getVelBase());
        
        if(y > Game.HEIGHT + 20){
            handler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(game.imageLoader.getTruckImage(index), game.r.rx(x), game.r.ry(y),game.r.rx(64), game.r.ry(300),null);
        g.drawRect(game.r.rx(x+2), game.r.ry(y), game.r.rx(59), game.r.ry(300));
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x+1,y,60,300);
    }
    
    public void setImage(int i){
        index = i;
    }
    
    
    
}
