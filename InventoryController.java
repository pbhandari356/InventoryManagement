import java.util.Map;

/**
 * @author Prashant Bhandari
 *	 
 */
public class InventoryController implements InventoryManagementSystem {
	
	private Map<String,Item> inventory; 
	
	public InventoryController() {
		inventory = Inventory.getInventory();				
	}	
	
	@Override
	public PickingResult pickProduct(String productId, int amountToPick) {			
		PickingResult result;
		Item item = inventory.get(productId);
		synchronized(item) {
			if (!hasItem(productId)) {
				result = new PickingResult("Invalid Product");
			} else {
				synchronized(item) {
					if (!hasLevel(productId, amountToPick)) {
						result = new PickingResult("Insufficient Stock. Check back later");
					} else {										
						int curr_level = item.getLevel();			
						item.setLevel(curr_level-amountToPick);
						inventory.put(productId, item);
						result = new PickingResult("Succesfully picked " + amountToPick + " items for " + productId 
								+ ". New item level is " + item.getLevel()+"\n");
					}
				}
			}
			return result;
		}
	}
		
	@Override
	public synchronized RestockingResult restockProduct(String productId, int amountToRestock) {		
		RestockingResult result;
		Item item = inventory.get(productId);					
			if (!hasItem(productId)) {
				result = new RestockingResult("Invalid Product");
			} else {						
				synchronized(item) {
					try {
						Thread.sleep(15000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					item.setLevel(item.getLevel() + amountToRestock);
					inventory.put(productId, item);			
					result = new RestockingResult("Succesfully added " + amountToRestock + " items for " + productId 
							+ ". New item level is " + item.getLevel()+"\n");
				}
			}				
		return result;
	}
	
	
	
	/**
	 * @param productId : id of item to add level
	 * @return true if : Item with productId exists in the inventory, false otherwise 
	 */
	public boolean hasItem(String productId){
		if (inventory.containsKey(productId))
			return true;
		
		return false;
	}
	
	
	/**
	 * @param product_id id of item to be picked
	 * @param pickAmount level of item to be picked
	 * @return false if Item with product_id has level less than pickAmount, false otherwise
	 */
	public boolean hasLevel(String product_id, int pickAmount) {
		Item item = inventory.get(product_id);		
		if (item.getLevel() < pickAmount)
			return false; 
		
		return true;
	}
	
	
	/**
	 * @param prodcutId
	 * @return return the Item associated with productId 
	 */
	public Item displayItem(String prodcutId){		
		return inventory.get(prodcutId);
	}

	
}
