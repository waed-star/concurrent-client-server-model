import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Waed
 */

//This handler is responsible to send data from clients to server
//This handler is part of the client while the file ClientHandler.java
//is part of the server
public class ServerConnection implements Runnable{

    private Socket server;
    private BufferedReader in;
    private PrintWriter out;
    private static ArrayList<String> connectedWeatherStationsId;
    private static WeatherStations currentStation;
    private static WeatherStations firstConntecdStation;
    
    private static ArrayList<String> receivedStations;
    
    public ServerConnection(Socket s) throws IOException{
    
        server = s;
        in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        out = new PrintWriter(server.getOutputStream(),true);
        
    }

    @Override
    public void run() {
       
            
            try {
                
                 while(true) {
        
                    String serverResponse = in.readLine();
 
                    //As soon as the station is connected
                    //And the server has verified and accepted it
                    //The station starts sending it's data to the server
                    //every 6 seconds.
                    if (serverResponse.equals("Connection Accepted")) {
                        
                        System.out.println(getCurrentStaion().getId());
                        
                        while(true) {
                    
                            if (getCurrentStaion().getId().equals("W001")) {

                                out.println(StationOne.stationOne.getWeatherStationDetails());

                            }  else if (getCurrentStaion().getId().equals("W002")) {

                                out.println(StationTwo.stationTwo.getWeatherStationDetails());

                            }
                        
                            Thread.sleep(6000);
                        
                        }
                       
                    //As soon as the user client is connected
                    //And the server has verified and accepted it
                    //The user client starts listening for data from server
                    } else if (serverResponse.equals("User connection Accepted")) {
                    
                        System.out.println("Ready to receive weather station details from server");

                        while(true) {

                            String inputFromServer = in.readLine();

                            String removeBracketsFromInput = inputFromServer.substring(1, inputFromServer.length() - 1);

                            //Converts String To ArrayList
                            ArrayList<String> inputAsArrayList = new ArrayList<>(Arrays.asList(removeBracketsFromInput.split(",")));

                            setReceivedStations(inputAsArrayList);
                        
                        }
                    }
                    
                    if (serverResponse == null) break;
                    
                    //To prevent data leakage
                    break;
                }
                 
            } catch (IOException ex) {
                
                ex.getMessage();
                
            } catch (InterruptedException ex) {
                
                ex.getMessage();
                
        }  finally {
            
                try {
                    
                    in.close();
                    
                } catch (IOException ex) {
                    
                    ex.getMessage();
                     
                }
            }
    }
    
      
    public static void setCurrentStaion(WeatherStations _currentStation) {
    
        currentStation = _currentStation;
        
    }
    
    public static WeatherStations getCurrentStaion() {
    
        return currentStation;
        
    }
    
    public static WeatherStations getFirstConnectedStation() {
    
        return firstConntecdStation;
        
    }
    
    public static void setFirstConnectedStation(WeatherStations _currentStation) {
    
        firstConntecdStation = _currentStation;
        
    }
    
    public static synchronized ArrayList<String> getReceivedStations() {
    
        return receivedStations;
        
    }
    
    public static synchronized void setReceivedStations(ArrayList<String> _receivedStations) {
    
        receivedStations = _receivedStations;
        
    }
    
    public static synchronized ArrayList<String> getConnectedStationsId() {
    
        return connectedWeatherStationsId;
        
    }
}