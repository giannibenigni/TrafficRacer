/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author luca.salvadori
 */
public class Resaize {

    private final float RealX;
    private final float RealY;
    private final float LogicalX;
    private final float LogicalY;

    public Resaize(float RealX, float LogicalX, float LogicalY) {
        this.RealX = (int) RealX;
        this.LogicalX = (int) LogicalX;
        this.LogicalY = (int) LogicalY;
        this.RealY = (int) ((LogicalX / LogicalY) * this.RealX);
    }

    public int rx(float lX) {
        return (int) (lX * (RealX / LogicalX));
    }
    
    public int ry(float lY) {
        return (int) (lY * (RealY / LogicalY));
    }

}
