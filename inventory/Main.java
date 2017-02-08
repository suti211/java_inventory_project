package inventory;

public class Main {

	public static void main(String[] args) {

		StoreManager storeManager = new StoreManager();
		storeManager.addStorage(new PersistentStore());
		storeManager.addCDProduct("Melattica", 500, 12);
		storeManager.addBookProduct("Mesterségem a halál", 1200, 780);
		
		System.out.println("Total Price: " + storeManager.getTotalProductPrice());
		
		for (String i : storeManager.listProducts()){
			System.out.println(i);
		}

	}

}
