import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author Bhandari
 * 
 */
public class Client {
	
	private static String HOST = "localHost";
	private static int PORT = 8080;
	
	public static void main(String[] args) {
        try {
            new Client().startClient();
        } catch (Exception e) {
            System.out.println("Something falied: " + e.getMessage());
            e.printStackTrace();
        }
    }
    	
    public void startClient() {
        
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;        
        BufferedReader stdIn = null;
        
        try {            
            socket = new Socket(HOST, PORT);
            
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));            
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;
             
            while (true) {
            	
	            System.out.println(in.readLine());
	            System.out.println(in.readLine());
	            System.out.println(in.readLine());
	            System.out.println(in.readLine());
	            System.out.println(in.readLine());
	                        		            
	            if ((fromUser = stdIn.readLine()) != null) {                
	                out.println(fromUser);
	            }
	            
	            if (fromUser.equalsIgnoreCase("exit")) {
	            	System.out.println(in.readLine());
	            	break;
	            } else if (fromUser.equalsIgnoreCase("view")) {
	            	fromServer = in.readLine();
	            	System.out.println(fromServer);
	            	if ((fromUser = stdIn.readLine()) != null) {                
		                out.println(fromUser);
		            }
	            	fromServer = in.readLine();
	            	System.out.println(fromServer);
	            } else {	            
		            fromServer = in.readLine();
		            System.out.println(fromServer);
		
		            if ((fromUser = stdIn.readLine()) != null) {                
		                out.println(fromUser);
		            }
		            fromServer = in.readLine();
		            System.out.println(fromServer);
		            if ((fromUser = stdIn.readLine()) != null) {                
		                out.println(fromUser);
		            }
		            fromServer = in.readLine();
		            System.out.println(fromServer);
	            }
            }
        } catch (UnknownHostException e) {            
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't read/write from the connection: " + e.getMessage());
            System.exit(1);
        } finally {             
            try {
            	out.close();
				in.close();
				stdIn.close();
	            socket.close();
			} catch (IOException e) {				
				e.printStackTrace();
			}
            
        }
    }
	
}
