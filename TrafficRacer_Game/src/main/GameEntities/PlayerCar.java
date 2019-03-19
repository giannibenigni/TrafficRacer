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

public class PlayerCar extends GameObject {

    //private final Random r = new Random();
    private final Handler handler;
    private final Game game;

    private Image img;

    int startAnimationStatus = 120;

    public PlayerCar(int x, int y, ID id, Handler handler, Game game) {
        super(x, y, id);
        this.handler = handler;
        this.game = game;
        URL url = getClass().getResource("/Images/playerCar.png");
        try {
            img = ImageIO.read(url);
        } catch (IOException ex) {
            Logger.getLogger(PlayerCar.class.getName()).log(Level.SEVERE, null, ex);
        }
        velY = 0;
    }

    @Override
    public void tick() {

        x += velX;
        y += velY;

        if (game.gameState == 1) {
            x = Game.clamp(x, 100, Game.WIDTH - 164);

            if (startAnimationStatus > 0) {
                startAnimation();

            } else {
                game.addPoint(((x > (Game.WIDTH / 2)) ? 1 : 2) + game.getVelBase());
                collision();
            }
        } else {
            if (y > Game.HEIGHT + 20) {
                handler.removeObject(this);
            }
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, game.r.rx(x), game.r.ry(y), game.r.rx(64), game.r.ry(64), null);
        g.drawRect(game.r.rx(x + 10), game.r.ry(y), game.r.rx(45), game.r.ry(64));

    }

    @Override
    public Rectangle getHitbox() {
        return new Rectangle(x + 10, y, 45, 64);
    }

    private void collision() {

        for (GameObject gameObject : handler.object) {
            if (gameObject.getId() == ID.EnemyCar || gameObject.getId() == ID.EnemyTruck || gameObject.getId() == ID.Obstacle) {
                if (getHitbox().intersects(gameObject.getHitbox())) {
                    System.out.println("Collisione!");
                    velY = gameObject.getVelY();

                    game.gameState = -1;

                    game.net.sendMachResult(game.getPoint());

                }
            }
        }

    }

    private void startAnimation() {
        if (startAnimationStatus > 60) {
            game.VelBase++;
        } else {
            if (startAnimationStatus == 60) {
                setVelY(-17);
            }
            if (startAnimationStatus >= 43) {
                setVelY(43 - startAnimationStatus);
            }
            game.VelBase--;
            if (startAnimationStatus == 1) {
                game.carSpawner.enable(true);
            }
        }
        startAnimationStatus--;
    }

}
