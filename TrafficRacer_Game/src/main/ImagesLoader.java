package main;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import main.GameEntities.PlayerCar;

public class ImagesLoader {

    private Image carImg[];
    private Image truckImg[];
    private Image obstaclesImg[];

    public ImagesLoader() {
        URL url;
        carImg = new Image[6];
        truckImg = new Image[2];
        obstaclesImg = new Image[1];

        try {
            url = getClass().getResource("/Images/Enemy1.png");
            carImg[0] = ImageIO.read(url);
            url = getClass().getResource("/Images/Enemy2.png");
            carImg[1] = ImageIO.read(url);
            url = getClass().getResource("/Images/Enemy3.png");
            carImg[2] = ImageIO.read(url);
            url = getClass().getResource("/Images/Enemy11.png");
            carImg[3] = ImageIO.read(url);
            url = getClass().getResource("/Images/Enemy22.png");
            carImg[4] = ImageIO.read(url);
            url = getClass().getResource("/Images/Enemy33.png");
            carImg[5] = ImageIO.read(url);
            url = getClass().getResource("/Images/Enemy4.png");
            truckImg[0] = ImageIO.read(url);
            url = getClass().getResource("/Images/Enemy44.png");
            truckImg[1] = ImageIO.read(url);
            url = getClass().getResource("/Images/Obstacle1.png");
            obstaclesImg[0] = ImageIO.read(url);
        } catch (IOException ex) {
            Logger.getLogger(PlayerCar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Image getCarImage(int i) {
        if (i < 0 || i >= carImg.length) {
            System.out.println("main.ImagesLoader.getImage()");
            return null;
        }
        return carImg[i];
    }

    public Image getTruckImage(int i) {
        if (i < 0 || i > truckImg.length) {
            return null;
        }

        return truckImg[i];
    }

    public Image getObstaclesImage(int i) {
        if (i < 0 || i > obstaclesImg.length) {
            return null;
        }

        return obstaclesImg[i];
    }

}
