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
import network.network;

public class Game extends Canvas implements Runnable {
    public static final int WIDTH = 800;
    public static final int HEIGHT = WIDTH / 8 * 9;

    private Thread thread;

    private final Random ra;
    private final Handler handler;

    public final HUD hud;
    public final Resize r;

    public int VelBase;
    public int VelAcceleration;
    private int point;
    public int gameState; // 0 : game started 1: game played -1 : game Over

    public final CarSpawner carSpawner;
    public final ImagesLoader imageLoader;
    private final KeyInput keyInput;

    public final RenderPage pageRender;
    
    public network net;

    long tick = 0;

    public Game() {
        inizzializzazione();
        gameState = 0;
        
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

        handler.addObject(new BackGround(0, 0, ID.BackGround, handler, this));
        
        
        net = new network();
        net.startConnection();
        
        
        
    }
    
    
    
    private void inizzializzazione(){
        VelBase = 0;
        VelAcceleration = 0;
        point = 0;
    }

    /**
     * *
     * Parte il thread del gioco
     */
    public synchronized void start() {

        thread = new Thread(this);
        thread.start();

    }

    public synchronized void stop() {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.requestFocus();

        long lastTime = System.nanoTime();
        double amountOfTicks = 60;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        double delay;
        double avgMsCi = 0;
        double avgMsTick = 0;
        while (true) {
            long now = System.nanoTime();
            delta = now - lastTime;
            lastTime = now;

            tick();
            render();

            //  System.out.println("ms/tick: " + ((double) (System.nanoTime() - lastTime) / 1000000) + "      ms/cicle: " + delta / 1000000);
            avgMsCi += delta / 1000000;
            avgMsTick += ((double) (System.nanoTime() - lastTime) / 1000000);
            if (tick % 60 == 0) {
                System.out.println("avg ms/cicle: " + ((int) (avgMsCi / 6)) / 10.0 + " avg ms/tick: " + ((int) (avgMsTick / 6)) / 10.0);
                avgMsCi = 0;
                avgMsTick = 0;
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


    }

    private void tick() {
        tick++;

        if (tick % 500 == 0 && VelBase < 18) { //da impedire che la velocità aumenti nel menù
            System.out.println("Vel+");
            VelBase++;
        }

        keyInput.tick();
        handler.tick();

        hud.tick();

        carSpawner.spawn(tick);

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

    public void playGame() {
        inizzializzazione();
        gameState = 1;
        handler.removeAllObjectWith(ID.PlayerCar);
        handler.addObject(new PlayerCar((WIDTH / 2) + 20, HEIGHT, ID.PlayerCar, handler, this));
        hud.setRenderMenu(false);
        carSpawner.enable(false);
    }

}
