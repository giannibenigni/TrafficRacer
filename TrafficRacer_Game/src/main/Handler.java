
package main;

import java.awt.Graphics;
import java.util.LinkedList;


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
    
}
