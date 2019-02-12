package main;

import main.GameEntities.PlayerCar;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.GameEntities.BackGround;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 800;
    public static final int HEIGHT = WIDTH / 8 * 9;

    private Thread thread;
    public boolean running = false;

    private final Random ra;
    private final Handler handler;

    public final HUD hud;
    public final Resize r;

    public int VelBase = 0;
    public int VelAcceleration = 0;
    private int point = 0;

    private final CarSpawner carSpawner;
    public final ImagesLoader imageLoader;
    private final KeyInput keyInput;

    public final RenderPage pageRender;

    long tick = 0;

    public Game() {
        ra = new Random();
        imageLoader = new ImagesLoader();
        r = new Resize(1000, WIDTH, HEIGHT);
        handler = new Handler();

        carSpawner = new CarSpawner(handler, this);

        pageRender = new RenderPage(handler, this);
        keyInput = new KeyInput(handler, this);
        this.addKeyListener(keyInput);

        hud = new HUD(handler, this);

        new Window(WIDTH, HEIGHT, "Gioco", this);

        //Entita del gioco
        handler.addObject(new BackGround(0, 0, ID.BackGround, handler, this));
        //handler.addObject(new EnemyCar(0,0,ID.EnemyCar, handler));

        handler.addObject(new PlayerCar((WIDTH / 2) - 32, HEIGHT - 180, ID.PlayerCar, handler, this));

    }

    /**
     * *
     * Parte il thread del gioco
     */
    public synchronized void start() {

        thread = new Thread(this);
        thread.start();
        running = true;

    }

    public synchronized void stop() {
        try {
            running = false;
            renderPage(1);
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.requestFocus();

        long lastTime = System.nanoTime();
        double amountOfTicks = 200.0;
//        double amountOfFrame = 60;
        double ns = 1000000000 / amountOfTicks;
//        double rns = 1000000000 / amountOfFrame;
        double delta = 0;
//        double deltar = 0;
//        long timer = System.currentTimeMillis();
//        int frames = 0;

//        while (running) {
//            long now = System.nanoTime();
//            delta += (now - lastTime) / ns;
//            deltar += (now - lastTime) / rns;
//            lastTime = now;
//            while (delta >= 1) {
//                tick();
//                System.out.println("main.Game.run() TICK");
//                delta--;
//            }
//
//            while (running && deltar >= 1) {
//                render();
//                frames++;
//                deltar--;
//                System.out.println("main.Game.run() RENDER");
//            }
//
//            System.out.println("main.Game.run() OUT");
//
//            if (System.currentTimeMillis() - timer > 5000) {
//                timer += 5000;
//                System.out.println("FPS: " + frames / 5);
//                frames = 0;
//            }
//        }
        double delay;
        double avgMsCi = 0;

        while (running) {
            long now = System.nanoTime();
            delta = now - lastTime;
            lastTime = now;

            tick();
            render();

            //  System.out.println("ms/tick: " + ((double) (System.nanoTime() - lastTime) / 1000000) + "      ms/cicle: " + delta / 1000000);
            avgMsCi += delta / 1000000;
            if (tick % 30 == 0) {
                System.out.println("avg ms/cicle: " + avgMsCi / 30);
                avgMsCi = 0;
            }

            delay = lastTime + ns - System.nanoTime();
            if (delay > 0) {
                try {
                    int delayMs = 0;
                    if (delay > 999999) {
                        delayMs = (int) delay / 1000000;
                        delay -= delayMs * 1000000;
                    }

                    Thread.sleep(delayMs, (int) delay);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        stop();
    }

    private void tick() {
        tick++;

        if (tick % 500 == 0 && VelBase < 18) {
            System.out.println("Vel+");
            VelBase++;
        }

        keyInput.tick();
        handler.tick();
        carSpawner.spawn(tick);
        hud.tick();

    }

    private void render() {

        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, r.rx(WIDTH), r.ry(HEIGHT));

        handler.render(g);
        hud.render(g);

        g.dispose();

        bs.show();

    }

    public void renderPage(int index) {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        pageRender.renderPageNumbr(index, g);

        g.dispose();

        bs.show();
    }

    public static int clamp(int var, int min, int max) {
        if (var >= max) {
            return var = max;
        } else {
            if (var <= min) {
                return var = min;
            } else {
                return var;
            }
        }
    }

    public static void main(String args[]) {

        new Game();

    }

    public int getVelBase() {
        return VelBase + VelAcceleration;
    }

    public int getPoint() {
        return point;
    }

    public void addPoint(int point) {
        this.point += point;
    }

    public Resize R() {
        return r;
    }

}
