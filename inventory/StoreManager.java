package inventory;

import java.util.ArrayList;
import java.util.List;

public class StoreManager {

	private StoreCapable storage;

	public void addStorage(StoreCapable storage) {
		this.storage = storage;
	}

	public void addCDProduct(String name, int price, int tracks) {
		storage.storeCDProduct(name, price, tracks);
	}

	public void addBookProduct(String name, int price, int size) {
		storage.storeBookProduct(name, price, size);
	}

	public List<String> listProducts() {
		List<String> nameList = new ArrayList<String>();
		
		for(Product product : storage.getAllProduct()){
			nameList.add(product.getName());
		}
		
		return nameList;
	}

	public int getTotalProductPrice() {
		int sum = 0;

		List<Product> products = storage.getAllProduct();

		for (int i = 0; i < products.size(); i++) {
			sum += products.get(i).getPrice();
		}

		return sum;
	}

}
