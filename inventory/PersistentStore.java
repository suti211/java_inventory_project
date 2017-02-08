package inventory;

import java.util.ArrayList;
import java.util.List;

public class PersistentStore extends Store {

	private List<Product> products = new ArrayList<Product>();

	@Override
	public void storeCDProduct(String name, int price, int tracks) {
		store(createProduct("CD", name, price, tracks));

	}

	@Override
	public void storeBookProduct(String name, int price, int size) {
		store(createProduct("Book", name, price, size));
	}

	@Override
	public List<Product> getAllProduct() {
		return loadProducts();
	}

	@Override
	protected void storeProduct(Product product) {
		products.add(product);
	}

}
