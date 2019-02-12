package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KeyInput extends KeyAdapter {

    private final Handler handler;
    private final Game game;

    private boolean dx = false;
    private boolean sx = false;

    private boolean up = false;
    private boolean dw = false;

    private int countAcc;
    private int countDec;
    
    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_ESCAPE) {
            //game.running = false;
        }

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.PlayerCar) {
                //Eventi del player

                //Destra D
                if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
                    dx = true;

                    tempObject.setVelX(5);
                }
                //Sinistra A
                if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
                    sx = true;
                    tempObject.setVelX(-5);
                }

                //su w
                if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
                    if (up) {
                        if (countAcc <= 60) {
                            System.out.print("acc: ");
                            double acc = Math.log((countAcc / 10) + 1);
                            System.out.println(acc);
                            game.VelAcc = (int) ((double)game.VelAcc + acc);
                            countAcc++;
                        }

                    } else {
                        up = true;
                        countAcc = 20;
                    }

                }
                //giù s
                if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
                    dw = true;
                    if (dw) {
                        
                        if (countAcc <= 60) {
                            System.out.println("dec");
                            game.VelAcc = (int) ((double)game.VelAcc - Math.log((countAcc / 10) + 1));
                            countDec++;
                        }

                    } else {
                        dw = true;
                        countDec = 0;
                    }
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

                //su w
                if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
                    up = false;

                    if (dw) {

                    }
                }

                //giù s
                if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
                    dw = false;

                    if (up) {

                    }
                }
                return;
            }
        }
    }
}
