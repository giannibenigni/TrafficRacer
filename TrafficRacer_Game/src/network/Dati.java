/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.io.Serializable;

public class Dati implements Serializable {

    String type;
    int point;

    public Dati(String type, int value) {
        this.type = type;
        switch (type) {
            case "SINGLEPLAYER_MACH_RESULT": {
                point = value;
            }
        }
    }


    public int getPoint() {
        return point;
    }

    public String getType() {
        return type;
    }
}
