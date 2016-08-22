import java.util.HashMap;
import java.util.Map;

/**
 * @author Prashant Bhandari
 *	 
 */
public class Inventory {
	
	private static Map<String,Item> inventory;
	
	private Inventory() {
		inventory = new HashMap<String,Item>();		
	}
	
	public static Map<String,Item> getInventory(){
		if (inventory==null) {
			inventory = new HashMap<String,Item>();
			populateMap();
		}		
		return inventory; 
	}
	
	/**
	 * Populate Inventory
	 */
	private static void populateMap(){
		Item adidas = new Item("Adidas",20,"Aisle1");
		Item nike = new Item("Nike",21,"Aisle1");
		Item reebok = new Item("Reebok",22,"Aisle2");
		Item puma = new Item("Puma",23,"Aisle3");
		Item umbro = new Item("Umbro",24,"Aisle3");
		Item new_balance = new Item("New Balance",25,"Aisle2");		
		
		inventory.put(adidas.getProductId(), adidas);
		inventory.put(nike.getProductId(), nike);
		inventory.put(reebok.getProductId(), reebok);
		inventory.put(puma.getProductId(), puma);
		inventory.put(umbro.getProductId(), umbro);
		inventory.put(new_balance.getProductId(), new_balance);					
	}
}
