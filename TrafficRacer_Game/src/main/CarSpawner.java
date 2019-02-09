/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Random;
import main.GameEntities.EnemyCar;
import main.GameEntities.EnemyTruck;
import main.GameEntities.Obstacles;

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
    private static int POSITION_CENTER = 385;

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

            double rCar = Math.random() * 12;
            
            //sistema per gestire la probabilità
            if (rCar >= 4 && rCar < 6) {
                EnemyCar enemyCar = new EnemyCar(POSITION_1, SPAWN_Y, ID.EnemyCar, handler,game);
                enemyCar.setVelY(12);
                enemyCar.setImage(3 + ran.nextInt(3));

                handler.addObject(enemyCar);
            }

            if (rCar >= 6 && rCar < 8) {
                EnemyCar enemyCar = new EnemyCar(POSITION_2, SPAWN_Y, ID.EnemyCar, handler,game);
                enemyCar.setVelY(13);
                enemyCar.setImage(3 + ran.nextInt(3));

                handler.addObject(enemyCar);
            }
            if (rCar >= 8 && rCar < 10) {
                EnemyCar enemyCar = new EnemyCar(POSITION_3, SPAWN_Y, ID.EnemyCar, handler,game);
                enemyCar.setVelY(14);
                enemyCar.setImage(3 + ran.nextInt(3));

                handler.addObject(enemyCar);
            }

            if (rCar >= 0 && rCar < 1) {
                EnemyCar enemyCar = new EnemyCar(POSITION_4, SPAWN_Y, ID.EnemyCar, handler,game);
                enemyCar.setVelY(2);
                enemyCar.setImage(ran.nextInt(3));

                handler.addObject(enemyCar);
            }

            if (rCar >= 1 && rCar < 2) {
                EnemyCar enemyCar = new EnemyCar(POSITION_5, SPAWN_Y, ID.EnemyCar, handler,game);
                enemyCar.setVelY(3);
                enemyCar.setImage(ran.nextInt(3));

                handler.addObject(enemyCar);
            }

            if (rCar >= 2 && rCar < 3) {
                EnemyCar enemyCar = new EnemyCar(POSITION_6, SPAWN_Y, ID.EnemyCar, handler,game);
                enemyCar.setVelY(3);
                enemyCar.setImage(ran.nextInt(3));

                handler.addObject(enemyCar);
            }
            
            double rTruck = Math.random() * 50;
            
            if(rTruck >= 3 && rTruck < 4){
                EnemyTruck enemyTruck = new EnemyTruck(POSITION_4, -400, ID.EnemyTruck, handler, game);
                enemyTruck.setVelY(2);
                enemyTruck.setImage(0);
                
                handler.addObject(enemyTruck);
            }
            
            if(rTruck >= 1 && rTruck < 2){
                EnemyTruck enemyTruck = new EnemyTruck(POSITION_5, -400, ID.EnemyTruck, handler, game);
                enemyTruck.setVelY(2);
                enemyTruck.setImage(0);
                
                handler.addObject(enemyTruck);
            }
            
            if(rTruck >= 2 && rTruck < 3){
                EnemyTruck enemyTruck = new EnemyTruck(POSITION_6, -400, ID.EnemyTruck, handler, game);
                enemyTruck.setVelY(2);
                enemyTruck.setImage(0);
                
                handler.addObject(enemyTruck);
            }
            
            if(rTruck >= 4 && rTruck < 5){
                EnemyTruck enemyTruck = new EnemyTruck(POSITION_3, -400, ID.EnemyTruck, handler, game);
                enemyTruck.setVelY(12);
                enemyTruck.setImage(1);
                
                handler.addObject(enemyTruck);
            }
            
            if(rTruck >= 5 && rTruck < 6){
                EnemyTruck enemyTruck = new EnemyTruck(POSITION_2, -400, ID.EnemyTruck, handler, game);
                enemyTruck.setVelY(12);
                enemyTruck.setImage(1);
                
                handler.addObject(enemyTruck);
            }
            
            if(rTruck >= 6 && rTruck < 7){
                EnemyTruck enemyTruck = new EnemyTruck(POSITION_1, -400, ID.EnemyTruck, handler, game);
                enemyTruck.setVelY(12);
                enemyTruck.setImage(1);
                
                handler.addObject(enemyTruck);
            }
        }
        
        if (tick % 50 == 0) {
        
            double rCar = Math.random() * 12;
            
            //sistema per gestire la probabilità
            if (rCar >= 0 && rCar < 4) {
                Obstacles obstacle = new Obstacles(POSITION_CENTER, SPAWN_Y, ID.EnemyCar, handler,game);
                obstacle.setVelY(12);
                obstacle.setImage(0);

                handler.addObject(obstacle);
            }
        
        }
    }
}
