import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Prashant Bhandari
 *	 
 */
public class Server {
	
	final static int _portNumber = 8080;
    final static InventoryController inv = new InventoryController();
    static ServerSocket serverSocket;
    
    public static void main(String[] args) {
    	try{
			serverSocket = new ServerSocket(_portNumber);
		    while (true) {
		      Socket clientSocket = serverSocket.accept();
		      ClientThread cliThread = new ClientThread(clientSocket, inv);
		      cliThread.start();
		    }   
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
}

