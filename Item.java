/**
 * @author Prashant Bhandari
 *	 
 */
public class Item {
	
	private String productId; 
	private int level;
	private String location;
	
	public Item(String productId, int level, String location) {
		this.productId = productId;
		this.level = level;
		this.location = location;
	}
	
	public Item() {
		
	}

	public String getProductId() {
		return productId;
	}
	public void setProduct_id(String productId) {
		this.productId = productId;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Item [productId=" + productId + ", level=" + level + ", location=" + location + "]";
	}
	
}
