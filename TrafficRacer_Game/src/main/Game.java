package main;

import main.GameEntities.PlayerCar;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;
import main.GameEntities.BackGround;

public class Game extends Canvas implements Runnable{
    
    public static final int WIDTH = 800;
    public static final int HEIGHT = WIDTH / 8 * 9;
    
    private Thread thread;
    public boolean running = false;
    
    private final Random ra;
    private final Handler handler;
    
    public final HUD hud;
    public final Resaize r;
    
    private int VelB = 0;
    private int point = 0;
    
    private final CarSpawner carSpawner;
    public final ImagesLoader loader;
    
    
    public final RenderPage pageRender;
    
    long tick = 0;
    
    public Game(){
        ra = new Random();
        loader = new ImagesLoader();
        r = new Resaize(1000, WIDTH, HEIGHT);
        handler = new Handler(); 
        
        carSpawner= new CarSpawner(handler,this);
        
        pageRender = new RenderPage(handler, this);
        
        this.addKeyListener(new KeyInput(handler,this));
        
        hud = new HUD(handler,this);
        
        new Window(WIDTH, HEIGHT, "Gioco", this);
        
        //Entita del gioco
        handler.addObject(new BackGround(0, 0, ID.BackGround, handler,this));
        
        
        
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
            running = false;
            renderPage(1);
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run(){
        this.requestFocus();
        
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double amountOfFrame = 60;
        double ns = 1000000000 / amountOfTicks;
        double rns = 1000000000 / amountOfFrame;
        double delta = 0;
        double deltar = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            deltar += (now - lastTime) / rns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            
            
            while(running&&deltar >= 1){
                render();
                frames++;
                deltar--;
            }
            
            if(System.currentTimeMillis() - timer > 5000){
                timer += 5000;
                System.out.println("FPS: " + frames/5);
                frames = 0;
            }
        }
        stop();
    }
    
    private void tick(){
        tick++;

        if(tick % 500 == 0 && VelB < 18){
            System.out.println("Vel+");
            VelB++;
        }
        
        handler.tick();
        carSpawner.spawn(tick);
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
    
    public void renderPage(int index){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        
        pageRender.renderPageNumbr(index, g);
        
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
