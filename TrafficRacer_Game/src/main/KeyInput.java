package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
            //  game.running = false;
        }

        if (key == KeyEvent.VK_ENTER) {
            if (game.gameState == -1 || game.gameState == 0) {
                game.playGame();
            }
        }
        if (game.gameState == 1) {//da mettere se si sta giocando
            try {
                //Destra D
                if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
                    dx = true;
                    handler.playerCar.setVelX(5);
                    return;
                }

                //Sinistra A
                if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
                    sx = true;
                    handler.playerCar.setVelX(-5);
                    return;
                }

                //su w
                if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
                    up = true;
                    return;
                }
                //giù s
                if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
                    dw = true;
                }
            } catch (NullPointerException error) {
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (handler.playerCar == null) {
            handler.playerCar = handler.getAObject(ID.PlayerCar);
        }

        try {

            //Destra D
            if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
                dx = false;

                if (sx) {
                    handler.playerCar.setVelX(-5);
                } else {
                    handler.playerCar.setVelX(0);
                }
                return;
            }
            //Sinistra A
            if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
                sx = false;

                if (dx) {
                    handler.getAObject(ID.PlayerCar).setVelX(5);
                } else {
                    handler.getAObject(ID.PlayerCar).setVelX(0);
                }
                return;
            }

            //su w
            if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
                up = false;
                //   System.out.println("main.KeyInput.keyReleased() UP");
                countAcc = 0;
                return;
            }

            //giù s
            if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
                dw = false;
                countDec = 0;
            }
        } catch (NullPointerException error) {
        }
    }

    public void tick() {

        if (up) {
            if (countAcc <= 60) {

                int acc = (int) ((double) game.VelAcceleration + Math.log((countAcc / 10) + 1));

                if (acc < 5 && acc > -3) {
                    game.VelAcceleration = acc;
                    //System.out.println("acc: " + acc);
                }

                countAcc++;
            }
        }

        if (dw) {

            if (countDec <= 60) {
                int acc = (int) ((double) game.VelAcceleration - Math.log((countAcc / 10) + 1));

                if (acc < 5 && acc > -2) {
                    game.VelAcceleration = acc;
                    //System.out.println("dec: " + acc);
                }
                countDec++;
            }

        }
    }
}
