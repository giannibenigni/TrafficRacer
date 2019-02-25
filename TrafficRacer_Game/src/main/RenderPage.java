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
            case 2: {
                renderStart(g, 0);
            }
            break;
        }
    }

    public void renderStart(Graphics g, int pBase) {
        g.setColor(Color.black);
        g.fillRect(game.r.rx(Game.WIDTH / 2 - 170), game.r.ry((Game.HEIGHT / 2) - 300 + pBase), game.r.rx(330), game.r.ry(60 ));
        g.setColor(Color.white);
        g.setFont(new Font(Font.DIALOG, Font.BOLD, game.r.ry(50)));
        g.drawString("TRAFFIC RACER", game.r.rx(Game.WIDTH / 2 - 160), game.r.ry(Game.HEIGHT / 2 - 250 + pBase));

        g.setColor(Color.black);
        g.fillRect(game.r.rx(Game.WIDTH / 2 - 150), game.r.ry(Game.HEIGHT / 2 - 150 + pBase), game.r.rx(300), game.r.ry(60 ));
        g.setColor(Color.white);
        g.drawString("PRESS ENTER", game.r.rx(Game.WIDTH / 2 - 140), game.r.ry(Game.HEIGHT / 2 - 105 + pBase));

//        g.setColor(Color.black);
//        g.fillRect(game.r.rx(Game.WIDTH / 2 - 115), game.r.ry(Game.HEIGHT / 2 - 85 + pBase), game.r.rx(230), game.r.ry(60 ));
//        g.setColor(Color.white);
//        g.drawString("SETTINGS", game.r.rx(Game.WIDTH / 2 - 110), game.r.ry(Game.HEIGHT / 2 - 35 + pBase));
//
//        g.setColor(Color.black);
//        g.fillRect(game.r.rx(Game.WIDTH / 2 - 50), game.r.ry(Game.HEIGHT / 2 - 20 + pBase), game.r.rx(85), game.r.ry(60));
//        g.setColor(Color.white);
//        g.drawString("Exit", game.r.rx(Game.WIDTH / 2 - 46), game.r.ry(Game.HEIGHT / 2 - -30 + pBase));
    }

    public void renderGameOver(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(game.r.rx(Game.WIDTH / 2 - 130), game.r.ry(Game.HEIGHT / 2 - 50), game.r.rx(250), game.r.ry(60));
        g.setColor(Color.white);
        g.setFont(new Font(Font.DIALOG, Font.BOLD, game.r.ry(50)));
        g.drawString("GAME OVER", game.r.rx(Game.WIDTH / 2 - 125), game.r.ry(Game.HEIGHT / 2 - 5));

        g.setColor(Color.black);
        g.fillRect(game.r.rx(Game.WIDTH / 2 - 130), game.r.ry(Game.HEIGHT / 2 - -14), game.r.rx(250), game.r.ry(60));
        g.setColor(Color.white);
        g.drawString("PLAY AGAIN", game.r.rx(Game.WIDTH / 2 - 126), game.r.ry(Game.HEIGHT / 2 - -60));
    }
}
