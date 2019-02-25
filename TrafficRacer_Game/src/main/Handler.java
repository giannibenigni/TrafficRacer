
package main;

import java.awt.Graphics;
import java.util.LinkedList;


public class Handler {
    public LinkedList<GameObject> object = new LinkedList<GameObject>();
    public GameObject playerCar;
    
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
        if(object.id==ID.PlayerCar){
            playerCar= object;
        }
        this.object.add(object);
    }
    
    public void removeObject(GameObject go){
        object.remove(go);
    }
    
    public GameObject getAObject(ID id){
        for (GameObject gameObject : object) {
            if(gameObject.id==id){
                return gameObject;
            }
        }
        return null;
    }
    
    public synchronized void removeAllObjectWith(ID id){
        if(id==ID.PlayerCar){
            playerCar= null;
        }
        for (int i = 0; i < object.size(); i++) {
            if(object.get(i).id==id){
                object.remove(object.get(i));
            }
        }
    }
    
}
