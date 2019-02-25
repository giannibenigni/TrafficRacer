/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Rectangle;
import java.util.Random;
import main.GameEntities.EnemyCar;
import main.GameEntities.EnemyTruck;
import main.GameEntities.Obstacles;

/**
 *
 * @author LS_Fisso
 */
public class CarSpawner {

    private static final int POSITION_1 = 125;
    private static final int POSITION_2 = 219;
    private static final int POSITION_3 = 315;
    private static final int POSITION_4 = 420;
    private static final int POSITION_5 = 520;
    private static final int POSITION_6 = 620;
    private static final int POSITION_CENTER = 385;

    private static final int SPAWN_Y = -300;

    private static final double SPAWN_RATE = 240.0; //inserire nultipli di 40 (maggiore il numero meno frequente lo spawn)

    private boolean enable;

    private final Random ran;

    private final Handler handler;
    private final Game game;

    public CarSpawner(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
        ran = new Random();
        enable = true;
    }

    public synchronized void spawn(long tick) {
        if (enable) {
            if (tick % ((int) ((1.0 / (game.VelBase + game.VelAcceleration + 8.0)) * SPAWN_RATE)) == 0) {

                for (int i = 0; i < 3 && !SpawnCar(); i++) { //provo per tre volte a spawnare una macchina finchè non ci riesco
                }

                for (int i = 0; i < 3 && !SpawnTruck(); i++) { //provo per tre volte a spawnare un camion finchè non ci riesco
                }
            }

            if (tick % ((int) ((1.0 / ((double) (game.VelBase + game.VelAcceleration + 8))) * 1200)) == 0) {
                Obstacles obstacle = new Obstacles(POSITION_CENTER, SPAWN_Y, ID.EnemyCar, handler, game);
                obstacle.setVelY(8);
                obstacle.setImage(0);

                handler.addObject(obstacle);
            }
        }
    }

    public boolean SpawnCar() {
        double rCar = Math.random() * 12;

        //sistema per gestire la probabilità
        if (rCar >= 4 && rCar < 6) {
            return trySpawnCar(12, POSITION_1);
        }

        if (rCar >= 6 && rCar < 8) {
            return trySpawnCar(13, POSITION_2);
        }
        if (rCar >= 8 && rCar < 10) {
            return trySpawnCar(14, POSITION_3);
        }

        if (rCar >= 0 && rCar < 1) {
            return trySpawnCar(2, POSITION_4);
        }

        if (rCar >= 1 && rCar < 2) {
            return trySpawnCar(3, POSITION_5);
        }

        if (rCar >= 2 && rCar < 3) {
            return trySpawnCar(3, POSITION_6);
        }
        return false;
    }

    public boolean SpawnTruck() {
        double rTruck = Math.random() * 50;

        if (rTruck >= 3 && rTruck < 4) {
            return trySpawnTruck(2, POSITION_4);
        }

        if (rTruck >= 1 && rTruck < 2) {
            return trySpawnTruck(3, POSITION_5);
        }

        if (rTruck >= 2 && rTruck < 3) {
            return trySpawnTruck(3, POSITION_6);
        }

        if (rTruck >= 4 && rTruck < 5) {
            return trySpawnTruck(14, POSITION_3);
        }

        if (rTruck >= 5 && rTruck < 6) {
            return trySpawnTruck(13, POSITION_2);
        }

        if (rTruck >= 6 && rTruck < 7) {
            return trySpawnTruck(12, POSITION_1);
        }
        return false;
    }

    public boolean trySpawnCar(int vel, int position) {
        if (!colide(new Rectangle(position + 10, SPAWN_Y, 45, 75))) {
            EnemyCar enemyCar = new EnemyCar(position, SPAWN_Y, ID.EnemyCar, handler, game);
            enemyCar.setVelY(vel);
            enemyCar.setImage((position < POSITION_CENTER ? 3 : 0) + ran.nextInt(3));

            handler.addObject(enemyCar);
            return true;
        }

        return false;
    }

    public boolean trySpawnTruck(int vel, int position) {
        if (!colide(new Rectangle(position + 1, SPAWN_Y, 60, 330))) {
            EnemyTruck enemyTruck = new EnemyTruck(position, -400, ID.EnemyTruck, handler, game);
            enemyTruck.setVelY(vel);
            enemyTruck.setImage((position < POSITION_CENTER ? 1 : 0));

            handler.addObject(enemyTruck);
            return true;
        }

        return false;
    }

    public boolean colide(Rectangle hitbox) {
        for (GameObject gameObject : handler.object) {
            if (gameObject.getId() == ID.EnemyCar || gameObject.getId() == ID.EnemyTruck || gameObject.getId() == ID.Obstacle) {
                if (hitbox.intersects(gameObject.getHitbox())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void enable(boolean enable) {
        this.enable = enable;
    }
}
