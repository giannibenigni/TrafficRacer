/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LS_Fisso
 */
public class network {

    private ObjectInputStream in = null;
    private ObjectOutputStream out = null;
    private Socket socket = null;

    public void startConnection() {
        try {

            socket = new Socket("localhost", 6969);
            System.out.println("Connesso");

            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            System.out.println("reading");

        } catch (Exception e) {
            System.out.println("di po");
        }

    }
    
    public void sendMachResult(int point){
        try {
            out.writeObject(new Dati("SINGLEPLAYER_MACH_RESULT", point));
        } catch (Exception ex) {
            Logger.getLogger(network.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
