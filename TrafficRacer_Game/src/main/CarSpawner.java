/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Random;
import main.GameEntities.EnemyCar;

/**
 *
 * @author LS_Fisso
 */
public class CarSpawner {

    private static int POSITION_1 = 125;
    private static int POSITION_2 = 219;
    private static int POSITION_3 = 315;
    private static int POSITION_4 = 420;
    private static int POSITION_5 = 520;
    private static int POSITION_6 = 620;

    private static int SPAWN_Y = -100;
    
    private Random ran;
    
    Handler handler;
    Game game;

    public CarSpawner(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
        ran = new Random();
    }

    public void spawn(long tick) {
        if (tick % 30 == 0) {

            double r = Math.random() * 10;
            
            //sistema per gestire la probabilità
            if (r >= 4 && r < 6) {
                EnemyCar enemyCar = new EnemyCar(POSITION_1, SPAWN_Y, ID.EnemyCar, handler,game);
                enemyCar.setVelY(12);
                enemyCar.setImage(3 + ran.nextInt(3));

                handler.addObject(enemyCar);
            }

            if (r >= 6 && r < 8) {
                EnemyCar enemyCar = new EnemyCar(POSITION_2, SPAWN_Y, ID.EnemyCar, handler,game);
                enemyCar.setVelY(13);
                enemyCar.setImage(3 + ran.nextInt(3));

                handler.addObject(enemyCar);
            }
            if (r >= 8 && r < 10) {
                EnemyCar enemyCar = new EnemyCar(POSITION_3, SPAWN_Y, ID.EnemyCar, handler,game);
                enemyCar.setVelY(14);
                enemyCar.setImage(3 + ran.nextInt(3));

                handler.addObject(enemyCar);
            }

            if (r >= 0 && r < 1) {
                EnemyCar enemyCar = new EnemyCar(POSITION_4, SPAWN_Y, ID.EnemyCar, handler,game);
                enemyCar.setVelY(2);
                enemyCar.setImage(ran.nextInt(3));

                handler.addObject(enemyCar);
            }

            if (r >= 1 && r < 2) {
                EnemyCar enemyCar = new EnemyCar(POSITION_5, SPAWN_Y, ID.EnemyCar, handler,game);
                enemyCar.setVelY(3);
                enemyCar.setImage(ran.nextInt(3));

                handler.addObject(enemyCar);
            }

            if (r >= 2 && r < 3) {
                EnemyCar enemyCar = new EnemyCar(POSITION_6, SPAWN_Y, ID.EnemyCar, handler,game);
                enemyCar.setVelY(3);
                enemyCar.setImage(ran.nextInt(3));

                handler.addObject(enemyCar);
            }
        }
    }
}
