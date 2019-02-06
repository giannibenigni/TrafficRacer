
package main;

import main.GameEntities.PlayerCar;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;
import main.GameEntities.BackGround;
import main.GameEntities.EnemyCar;

public class Game extends Canvas implements Runnable{
    
    public static final int WIDTH = 800;
    public static final int HEIGHT = WIDTH / 8 * 9;
    
    private Thread thread;
    private boolean running = false;
    
    private Random ra;
    private Handler handler;
    private HUD hud;
    public Resaize r;
    
    private int VelB = 0;
    private int point = 0;
    
    CarSpawner carSpawner;
    public ImagesLoader loader;
    
    
    long tick = 0;
    
    public Game(){
        ra = new Random();
        loader = new ImagesLoader();
        r = new Resaize(1000, WIDTH, HEIGHT);
        handler = new Handler(); 
        
        carSpawner= new CarSpawner(handler,this);
        
        this.addKeyListener(new KeyInput(handler));
        
        hud = new HUD(handler,this);
        
        new Window(WIDTH, HEIGHT, "Gioco", this);
        
        //Entita del gioco
        handler.addObject(new BackGround(0, 0, ID.BackGround, handler,this));
        //handler.addObject(new EnemyCar(0,0,ID.EnemyCar, handler));
        handler.addObject(new PlayerCar((WIDTH/2)-32, HEIGHT-180, ID.PlayerCar, handler,this));   
        
    }
    
    /***
     * Parte il thread del gioco
     */
    public synchronized void start(){
        
        thread = new Thread(this);
        thread.start();
        running = true;
        
    }
    
    public synchronized void stop(){
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run(){
        
        
        this.requestFocus();
        
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running){
                render();
                frames++;
            }
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }
    
    private void tick(){
        tick++;

        if(tick%100 == 0){
            System.out.println("Vel+");
            VelB++;
        }
        
        carSpawner.spawn(tick);
        handler.tick();
        hud.tick();
        
    }
    
    private void render(){
        
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
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
    
    public static int clamp(int var, int min, int max){
        if(var >= max){
           return var = max; 
        }else{
            if(var <= min){
                return var = min;
            }else{
                return var;
            }
        }
    }
    
    public static void main(String args[]){
        
        new Game();
        
    }
    
    public int getVelB(){
        return VelB;
    }
    
    public int getPoint(){
        return point;
    }
    
    public void addPoint(int point){
        this.point += point;
    }
    
    public Resaize R(){
        return r;
    }
    
}
