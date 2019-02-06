package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KeyInput extends KeyAdapter {

    private Handler handler;

    private boolean dx = false;
    private boolean sx = false;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_ESCAPE) {
            
        }

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.PlayerCar) {
                //Eventi del player

                //Destra D
                if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
                    dx = true;
//                    if(sx){
//                        tempObject.setVelX(2);
//                    }else{
//                    tempObject.setVelX(5);}
                    tempObject.setVelX(5);
                }
                //Sinistra A
                if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
                    sx = true;
//                    if(dx){
//                        tempObject.setVelX(-2);
//                    }else{
//                    tempObject.setVelX(-5);}
                    tempObject.setVelX(-5);
                }
                return;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.PlayerCar) {
                //Eventi del player

                //Destra D
                if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
                    dx = false;
                    tempObject.setVelX(0);
                    if (sx) {
                        tempObject.setVelX(-5);
                    }
                }
                //Sinistra A
                if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
                    sx = false;
                    tempObject.setVelX(0);
                    if (dx) {
                        tempObject.setVelX(5);
                    }
                }
                return;
            }
        }
    }
}
