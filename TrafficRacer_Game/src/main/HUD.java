package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import static java.awt.font.TextAttribute.FONT;


public class HUD {
    
    public static long Punteggio = 0;
    
    public void tick(){
        Punteggio++;
    }
    
    public void render(Graphics g){
        
        g.setColor(Color.white);
        g.setFont(new Font("Serif", Font.BOLD,30));
        g.drawString("Score: " + String.valueOf(Punteggio),20,50);
    }
}
