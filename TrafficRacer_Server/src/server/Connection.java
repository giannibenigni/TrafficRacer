package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import network.Dati;





public class Connection implements Runnable{
    
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
        } catch (IOException e) {
            try {
                client.close();
            } catch (IOException ex) {
                
            }
        }
        
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {            
            try {
                Dati dati = (Dati) in.readObject();
                System.out.println("Dati : "+ dati.getType());
                if(dati.getType().equals("SINGLEPLAYER_MACH_RESULT")){
                    System.out.println("SINGLEPLAYER_MACH_RESULT: point"+ dati.getPoint());
                }
                
            } catch (IOException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    
    
}
