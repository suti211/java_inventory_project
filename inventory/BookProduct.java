package inventory;

public class BookProduct extends Product {

	private int pageSize;
	
	public BookProduct(String name, int price, int pageSize){
		this.name = name;
		this.pageSize = pageSize;
		this.price = price;
	}

	public int getPageSize() {
		return pageSize;
	}
	
	
	
}
