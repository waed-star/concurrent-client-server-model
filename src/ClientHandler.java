import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 *
 * @author Waed
 */

//This client handler is responsible to receive data from clients
//Once the server has confirmed the connection is a station or a client
//It sends back a accepted signal
//This mimics a TCP/IP connection
public class ClientHandler implements Runnable{

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private ArrayList<ClientHandler> clients;
    
    private static WeatherStations currentStation;
    
    public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> clients) throws IOException{
        this.client = clientSocket;
        this.clients = clients;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(),true);
    }
    
    @Override
    public void run() {
        
        try{
            while(true){
                //First the server reads string from the clients to
                //distinguish between client connections
                String inputFromClient = in.readLine();

                //Converts String To ArrayList
                List<String> inputAsArrayList = new ArrayList<>(Arrays.asList(inputFromClient.split(",")));
                
                String fieldContainsId = inputAsArrayList.get(0);
  
                char firstLetterOfId = inputAsArrayList.get(0).charAt(0);

                //If the id starts with W, then it's a weather station
                if (firstLetterOfId == 'W') {

                    System.out.println("[" + fieldContainsId + "]" + " Weather client connected!");
                    
                    out.println("Connection Accepted");
                    
                    //Station registering it's id in the server
                    Server.connectedWeatherStationsId.add(fieldContainsId);
                    
                    System.out.println("Weather stations currently connected: " + Server.connectedWeatherStationsId);

                    System.out.println("Data received successfully for station: " + inputAsArrayList);
                    
                    Server.addConnectedStations(inputFromClient);
                    
                    System.out.println("All weather station details: " + Server.getConnectedStations());
                    
                    while(true) {
                    
                        String upToDate = in.readLine();
                        
                        if (upToDate == null) {
                            
                            this.client.close();
                            System.out.println("[" + fieldContainsId + "] is disconnected");
                            int positionOfId = Server.connectedWeatherStationsId.indexOf(fieldContainsId);
                            Server.connectedWeatherStationsId.remove(positionOfId);
                            
                            break;
                        }
                                                
                        if (fieldContainsId.equals("W001")) {
                        
                            System.out.println("Up to date details: " + upToDate);
                            Server.setUpToDateDetails(upToDate);
                            Server.setDropDownMenuItems(0,upToDate);
                            
                        } else if (fieldContainsId.equals("W002")) {
                            
                            System.out.println("Up to date details: " + upToDate);
                            Server.setUpToDateDetails(upToDate);
                            Server.setDropDownMenuItems(1,upToDate);
                        
                        } 
                        

                        
                        /*The thread sleeps for 1 seconds because data is sent from
                        station every 5 second. To make sure the server receives items
                        from all connected stations, it is refreshed every second*/
                        Thread.sleep(1000);
                        
                    }
  
                //If the id starts with U, then it's a user client
                } else if (firstLetterOfId == 'U') {
                
                    //This code is repsonsible to sends data to user
                    System.out.println("[" + fieldContainsId + "] " + "User client connected!");
                    
                    out.println("User connection Accepted");
                    
                    //Client registering it's id in ther server
                    Server.connectedUserClientsId.add(fieldContainsId);
                    
                    System.out.println("User client currently connected: " + Server.connectedUserClientsId);

                    while (true) {
                        
                        out.println(Server.getDropDownMenuItems());
                         
                        //I used thread here because in.readLine() stops the rest of the code 
                        //from running if a message is not sent.
                        //Therefore I used a thread to run both codes in the same time
                        Thread t = new Thread(){
                        public void run(){
                                 while (true) {
                                      
                                    try{
                                        String isConnected = in.readLine();

                                        System.out.println("isConnected: " + isConnected);

                                        if (isConnected.equals(fieldContainsId + " Has disconnected")) {

                                            System.out.println("[" + fieldContainsId + "] is disconnected");
                                            int positionOfId = Server.connectedUserClientsId.indexOf(fieldContainsId);
                                            Server.connectedUserClientsId.remove(positionOfId);

                                            break;
                                        } 
                                    } catch (IOException e) {
                                        e.getStackTrace();
                                    }
                                }
                            }
                        };
                                
                       t.start();

                        /*The thread sleeps for 1 seconds because data is sent from
                        station every 5 second. To make sure the server sends items
                        from all connected stations, it is refreshed every second*/
                        Thread.sleep(1000);
                    }
                    

  
                } else if (inputFromClient == null) {System.out.println("Empty!");}
                   
                break;
                    
            }
        } catch(IOException e) {
        
            System.err.println("IO exception in client handler");
            
        } catch (InterruptedException ex) {
            
            ex.getMessage();
            
        } finally {
            
            out.close();
            
            try {
                
                in.close();
                
            } catch (IOException ex) {
                
                ex.getMessage();
                
            }
        }
                
   }
    
    private void outToAll(String msg) {
    
        for(ClientHandler aClient : clients) {
        
            aClient.out.println(msg);
            
        }
    }
    
    public static synchronized void setCurrentStation(WeatherStations _currentStation) {
    
        currentStation = _currentStation;
        
    }
    
    synchronized WeatherStations getCurrentStation() {
            
        return currentStation;
        
    }
    
}
