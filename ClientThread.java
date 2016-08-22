import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Prashant Bhandari
 *	 
 */
public class ClientThread extends Thread {
	private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private InventoryController inv_con = null;
        
    public ClientThread(Socket socket, InventoryController inv_con) {
        this.socket = socket;
        this.inv_con = inv_con;
    }
    
    public void run() {
        System.out.println("Client connected to socket: " + socket.toString());
        
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));            
            String operation;
            while (true) {
	            out.println("Please choose an option:");
	            out.println("\"Add\" to Restock Item");
	            out.println("\"Remove\" to Pick Item");
	            out.println("\"View\" to View Inventory");
	            out.println("\"Exit\" to Quit");
	            	            
	            if ((operation = in.readLine()) != null) {	                
	                if (!operation.equalsIgnoreCase("exit")) {                	
	            		performOperation(operation);                
	            	} else {
	            		out.println("Thank you for using the system");
	            		System.out.println(socket.getPort() +": EXIT" );
	            		try {
	                        out.close();
	                        in.close();
	                        socket.close();	                        
	                        break;
	                    } catch(Exception e) { 
	                        System.out.println("Couldn't close I/O streams");
	                    }
	            	}
	            }
            }	        
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
			e.printStackTrace();
		} finally { 
            try {
                out.close();
                in.close();
                socket.close();
            } catch(Exception e) { 
                System.out.println("Couldn't close I/O streams");
            }
		}
	}
    
    public void performOperation(String operation) throws InterruptedException, IOException {
    	
    	if (operation.equalsIgnoreCase("add")){			
			out.println("Please enter product id:");
			String productId = in.readLine();
			System.out.println(socket.getPort() +": Product Requested " + productId);
			
			out.println("Please enter level to add:");
			int amountToRestock = Integer.parseInt(in.readLine());					
			System.out.println(socket.getPort() +": Amount to Restock " + amountToRestock);
			
			RestockingResult result = inv_con.restockProduct(productId, amountToRestock);					
			out.println(result.getMessage());
			System.out.println(socket.getPort() + " restocked product " + productId);			
			
		} else if (operation.equalsIgnoreCase("remove")) {			
			out.println("Please enter product id:");
			String productId = in.readLine();
			System.out.println(socket.getPort() +": Product Requested " + productId);
			
			out.println("Please enter level to remove:");
			int amountToPick = Integer.parseInt(in.readLine());					
			System.out.println(socket.getPort() +": Amount to Pick " + amountToPick);
			
			PickingResult result = inv_con.pickProduct(productId, amountToPick);
			
			out.println(result.getMessage());
			System.out.println(socket.getPort() + " picked product " + productId);
			
		} else if (operation.equalsIgnoreCase("view")){
			out.println("Please enter product id:");
			String productId = in.readLine();						
			out.println(inv_con.displayItem(productId));
			System.out.println(socket.getPort() + " viewed product " + productId);
		}    	
	}
	
}
