import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Waed
 */
public class Server implements Runnable{
    
    private static final int PORT = 9090;
    
    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4);
    
    //The following variables are used to register the clients id on the server.
    public static ArrayList<String> connectedWeatherStationsId = new ArrayList<>();
    public static ArrayList<String> connectedUserClientsId = new ArrayList<>();
    
     //The following variables are used save weather station details.
    //It is used to help weather stations upload their data
    private static ArrayList<String> connectedWeatherStationsDetails = new ArrayList<>();
    
    //This variable is used to represent items on the combobox
    private static ArrayList<String> dropDownMenuItems = new ArrayList<>(Arrays.asList("",""));
    
    public static WeatherStations currentStation;
    
    private static String upToDateDetails;
    
    public Server() throws IOException{
        
        ServerSocket listener = new ServerSocket(PORT);
        
        while(true){
            System.out.println("[Server] Waiting for client connection...");
            Socket client = listener.accept();
            
            ClientHandler clientThread = new ClientHandler(client,clients);
            clients.add(clientThread);

            pool.execute(clientThread);
        }
    }

    @Override
    public void run(){
        try{
            
            Server server = new Server();
        
        } catch (IOException e) {
            
            e.printStackTrace();
            
        }
    }
    
    
    public static synchronized void addConnectedStations(String station) {
    
        connectedWeatherStationsDetails.add(station);
        
    }
    
    public static synchronized ArrayList<String> getConnectedStations() {
            
        return connectedWeatherStationsDetails;
        
    }
    
    public static synchronized void setUpToDateDetails(String station) {
    
        upToDateDetails = station;
        
    }
    
    public static synchronized String getUpToDateDetails() {
            
        return upToDateDetails;
        
    }
    
    public static synchronized void setDropDownMenuItems(int position, String station) {
    
        dropDownMenuItems.set(position, station);
        
    }
    
    public static synchronized ArrayList<String> getDropDownMenuItems() {
            
        return dropDownMenuItems;
        
    }
}
