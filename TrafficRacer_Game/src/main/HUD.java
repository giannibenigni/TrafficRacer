package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {

    private final Handler handler;
    private final Game game;

    public HUD(Handler handler, Game g) {
        this.handler = handler;
        this.game = g;
    }

    public void tick() {

    }

    public void render(Graphics g) {

        g.setColor(Color.white);
        g.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        g.drawString("Score: " + String.valueOf(game.getPoint()), game.r.rx(20), game.r.ry(50));
    }
}
