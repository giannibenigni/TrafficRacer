
package main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Handler {
    

    
    public LinkedList<GameObject> object = new LinkedList<GameObject>();
    
    public void tick(){
        
        
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }

    }
    
    public void render(Graphics g){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            tempObject.render(g);
        }
    }
    
    public void addObject(GameObject object){
        this.object.add(object);
    }
    
    public void removeAllObject(){
        this.object.remove(object);
    }
    
    public void removeObject(GameObject go){
                object.remove(go);
    }
    
}
