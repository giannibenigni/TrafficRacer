package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import static main.Game.HEIGHT;
import static main.Game.WIDTH;
import main.GameEntities.PlayerCar;

public class HUD {

    private final Handler handler;
    private final Game game;

    private int renderMenu;

    public HUD(Handler handler, Game g) {
        this.handler = handler;
        this.game = g;
        renderMenu = -1;
    }

    public void tick() {

    }

    public void render(Graphics g) {

        g.setColor(Color.white);
        g.setFont(new Font(Font.DIALOG, Font.BOLD, game.r.ry(30)));
        g.drawString("Score: " + String.valueOf(game.getPoint()), game.r.rx(20), game.r.ry(50));

        if (game.gameState == -1) {
            game.pageRender.renderGameOver(g);
        }

        if (renderMenu > 0) {
            if (renderMenu < 1000) {
                game.pageRender.renderStart(g, renderMenu);
                renderMenu += 15;
            }
        } else {
            game.pageRender.renderStart(g, 0);
        }

    }

    public void setRenderMenu(boolean renderMenu) {
        if (renderMenu) {           
                this.renderMenu = -1;           
        } else {
            if (this.renderMenu < 1000) {
                this.renderMenu = 1;
            }
            
        }

    }

}
