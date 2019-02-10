package main.GameEntities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import main.GameObject;
import main.Handler;
import main.ID;
import main.Game;

public class BackGround extends GameObject {
    
    private final Handler handler;
    private final  Game game;
    private final Random r;
    
    //Variabili per cambiare sfondo
    private Image imgs[];
    private int selectedImage;
    private int previousImage;
    private int y1;

    public BackGround(int x, int y, ID id, Handler handler,Game g) {
        super(x, y, id);
        this.handler = handler;
        this.game = g;
        r = new Random();

        //Caricamento immagini
        URL url;
        imgs = new Image[3];
        try {
            url = getClass().getResource("/Images/Sfondo.jpg");
            imgs[0] = ImageIO.read(url);
            url = getClass().getResource("/Images/Sfondo.jpg");
            imgs[1] = ImageIO.read(url);
            url = getClass().getResource("/Images/Sfondo.jpg");
            imgs[2] = ImageIO.read(url);

        } catch (IOException ex) {
            Logger.getLogger(PlayerCar.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Setto velocita dello sfondo
        velY = 8;
        
        //Setto come immagini iniziali le prime
        selectedImage = 0;
        previousImage = 0;

    }

    @Override
    public void tick() {
        y += velY + game.getVelB();
    }

    @Override
    public void render(Graphics g) {
        if (y >= Game.HEIGHT) {
            y = 0;
            //Seleziono le immagini casualmente
            previousImage = selectedImage;
            selectedImage = r.nextInt(3);
        }

        y1 = y - Game.HEIGHT;

        g.drawImage(imgs[previousImage], 0, game.r.ry(y), game.r.rx(Game.WIDTH), game.r.ry(Game.HEIGHT), null);
        g.drawImage(imgs[selectedImage], 0, game.r.ry(y1), game.r.rx(Game.WIDTH), game.r.ry(Game.HEIGHT), null);

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

}
