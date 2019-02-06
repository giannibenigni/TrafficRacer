package server;

public class TrafficRacer {

    private static Server s;
    
    public static void main(String[] args) {
        
        System.out.println("start");
        try {
            s = new Server();
        } catch (Exception e){}
        
    }
    
}
