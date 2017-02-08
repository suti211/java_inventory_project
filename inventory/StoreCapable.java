package inventory;

import java.util.List;

public interface StoreCapable {
	
	public List<Product> getAllProduct();
	public void storeCDProduct(String name, int price, int tracks);
	public void storeBookProduct(String name, int price, int size);
	
}
