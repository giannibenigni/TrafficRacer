package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import static java.awt.font.TextAttribute.FONT;


public class HUD {
    
    Handler handler;
    Game game;
    
    public HUD(Handler handler, Game g){
        this.handler = handler;
        this.game = g;
    }
    
    public void tick(){
        
    }
    
    public void render(Graphics g){
        
        g.setColor(Color.white);
        g.setFont(new Font("Serif", Font.BOLD,30));
        g.drawString("Score: " + String.valueOf(game.getPoint()),game.r.rx(20),game.r.ry(50));
    }
}
