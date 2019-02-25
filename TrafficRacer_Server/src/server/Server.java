package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
    
    private ServerSocket s;
    
    public Server() throws Exception{
        s = new ServerSocket(6969);
        System.out.println("Server partito con porta 6969");
        this.start();
    }

    @Override
    public void run() {
        
        while(true){
            try {
                Socket client = s.accept();
                Match match = new Match(new Connection(client));
            } catch (Exception e) {
            }
        }
        
    }
    
    
    
}
