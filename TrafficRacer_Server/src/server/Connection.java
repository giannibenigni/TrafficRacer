package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Connection extends Thread{
    
    private Socket client = null;
    private ObjectInputStream in = null;
    private ObjectOutputStream out = null;
    
    private Match nm;
    
    public Connection(Socket clientSocket){
        
        client = clientSocket;
        System.out.println("clientsocket" + client.getInetAddress());
        
        try {
            out = new ObjectOutputStream(client.getOutputStream());
            in = new ObjectInputStream(client.getInputStream());
        } catch (Exception e) {
            try {
                client.close();
            } catch (IOException ex) {
                
            }
        }
        
        this.start();
    }
    
}
