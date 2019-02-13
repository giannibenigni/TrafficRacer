package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {

    private final Handler handler;
    private final Game game;

    private boolean gameover;
    private int renderMenu;

    public HUD(Handler handler, Game g) {
        this.handler = handler;
        this.game = g;
        gameover = false;
        renderMenu = -1;
    }

    public void tick() {

    }

    public void render(Graphics g) {

        g.setColor(Color.white);
        g.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        g.drawString("Score: " + String.valueOf(game.getPoint()), game.r.rx(20), game.r.ry(50));

        if (gameover) {
            game.pageRender.renderGameOver(g);
        }
        if (renderMenu>0) {
            if(renderMenu<1000){
                game.pageRender.renderStart(g,renderMenu);
                renderMenu+=15;
            }
        }else{
            game.pageRender.renderStart(g,0);
        }

    }

    public void setGameover() {
        gameover = true;
    }

    public boolean isGameover() {
        return gameover;
    }

    public void setRenderMenu(boolean renderMenu) {
        if(renderMenu){
            this.renderMenu = -1;
        }else{
            this.renderMenu = 1;
        }
        
    }

}
