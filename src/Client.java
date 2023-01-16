import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Waed
 */
public class Client {
    
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 9090;
    private static Socket clientSocket;
    private static ServerConnection serverConn;
    
    public Client() throws IOException{
        
        clientSocket = new Socket(SERVER_IP,SERVER_PORT);

        ServerConnection serverConn = new ServerConnection(clientSocket);
        
    }
    
    public static void sendData(String stationID) throws IOException{
        //Goes out to the server
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
        
        new Thread(serverConn).start();
        
         out.println(stationID);
    }
    
    public static void setSocket(Socket currentSocket) {
        clientSocket = currentSocket;
    }
    
    
//    public static void main(String[] args) throws IOException{
//        
//        Socket socket = new Socket(SERVER_IP,SERVER_PORT);
//
//        ServerConnection serverConn = new ServerConnection(socket);
//        
//        //Used to read from server
//        //BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
//        //line 24 is replaced by line 21
//        
//        //Reads from the keyboard
//        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
//        
//        //Goes out to the server
//        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
//        
//        new Thread(serverConn).start();
//        
//        while(true){
//            String command = keyboard.readLine(); //Reads from keyboard
//
//            if (command.equals("quit")) break;
//            
//            //Sends command to server
//            out.println(command);
//        }
//        
//        socket.close();
//        System.exit(0);
//    }
    
}
