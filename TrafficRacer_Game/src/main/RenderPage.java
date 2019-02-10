/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author LS_Fisso
 */
public class RenderPage {

    private final Handler handler;
    private final Game game;

    public RenderPage(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
    }

    public void renderPageNumbr(int index, Graphics g) {
        switch (index) {
            case 1: {
                renderGameOver(g);
            }
            break;
        }
    }

    public void renderGameOver(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(game.r.rx(Game.WIDTH / 2 - 130), game.r.ry(Game.HEIGHT / 2 - 50), game.r.rx(250), game.r.ry(60));
        g.setColor(Color.white);
        g.setFont(new Font(Font.DIALOG, Font.BOLD, 50));
        g.drawString("GAME OVER", game.r.rx(Game.WIDTH / 2 - 125), game.r.ry(Game.HEIGHT / 2 - 5));
    }
}
